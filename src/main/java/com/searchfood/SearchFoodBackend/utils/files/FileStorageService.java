package com.searchfood.SearchFoodBackend.utils.files; 

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.multipart.MultipartFile; 

import org.springframework.core.io.Resource; 
import org.springframework.core.io.UrlResource; 
import org.springframework.util.StringUtils; 

import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.nio.file.StandardCopyOption; 
import java.net.MalformedURLException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.properties.FilesStorageProperties; 
import com.searchfood.SearchFoodBackend.utils.exceptions.FileStorageException; 

@Service 
public class FileStorageService{ 

    private static final Logger log = LoggerFactory.getLogger( FileStorageService.class ); 
    private final Path fileStorageLocation; 

    @Autowired 
    public FileStorageService( FilesStorageProperties filesStorageProperties ){ 

        this.fileStorageLocation = Paths.get( filesStorageProperties.getUploadDir() ) 
                                        .toAbsolutePath()
                                        .normalize(); 
        log.debug( "fileStorageLocation: " + this.fileStorageLocation ); 

        try{ // try to create a a directory if not exist. 
            Files.createDirectories( this.fileStorageLocation ); 
        }catch( Exception e ){ 
            throw new FileStorageException("Coun't not create the directory where the uploaded files will be stored."); 
        } 

    } 

    public String storeFile( MultipartFile file ){ 
        String fileName = StringUtils.cleanPath( file.getOriginalFilename() ); // Using StringUtils.cleanPath() to clean the ".." or ".", or normalize the path. 

        try{ 
            if( fileName.contains("..") ) throw new FileStorageException("Couldn't store file name with \"..\""); 

            Path targetLocation = this.fileStorageLocation.resolve( fileName ); // concat fileName to the fileStorageLocation. 
            log.debug( "fileName: " + fileName ); 
            log.debug("targetLocation: " + targetLocation ); 
            Files.copy( file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING ); // Copy all bytes from an input stream to a file. 
            // org.springframework.web.multipart.getInputStream() return an InputStream to read the contents of the file from. 

            return fileName; 
        }catch( IOException e ){ 
            throw new FileStorageException( "Couldn't store files " + fileName + ". Please try again." ); 

        } 
    } 

} 
