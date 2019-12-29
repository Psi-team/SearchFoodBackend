package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.springframework.web.multipart.MultipartFile; 
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 
import org.springframework.util.StringUtils; 

import org.springframework.core.io.ClassPathResource; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import java.util.List; 
import java.util.Arrays; 
import java.util.stream.Collectors; 

import java.io.IOException; 

import com.searchfood.SearchFoodBackend.utils.files.FileStorageService; 
import com.searchfood.SearchFoodBackend.utils.files.UploadFileResponse; 
import com.searchfood.SearchFoodBackend.utils.exceptions.FilesException; 

@RestController 
public class FilesController{ 

    private static final Logger log = LoggerFactory.getLogger( FilesController.class ); 

    @Autowired 
    private FileStorageService fileStorageService; 

    @PostMapping("/uploadFile") 
    public ResponseEntity<?> uploadFile( @RequestParam("file") MultipartFile file ){ 

        log.debug("Start to uploading files..."); 
        log.debug( file.toString() ); 
        String targetLocation = fileStorageService.storeFile( file ); 
        log.debug( "targetLocation: " + targetLocation ); 

        if ( StringUtils.isEmpty( targetLocation.endsWith("/") ) ) throw new FilesException("Please choose a images"); 

        //String fileDownloadURI = 
        //                     ServletUriComponentsBuilder
        //                            .fromCurrentContextPath()
        //                            .path("/uploadFile/") 
        //                            .path(fileName) 
        //                            .toUriString(); 
        //ClassPathResource imgFileUrl = new ClassPathResource("uploads/"+fileName); 
        //log.debug("imgFileUrl: " + imgFileUrl); 
        //log.debug( "fileDownloadURI: " + fileDownloadURI ); 

        //return new ResponseEntity<>( 
        //            new UploadFileResponse( fileName, fileDownloadURI, 
        //                file.getContentType(), file.getSize() ), 
        //            HttpStatus.CREATED ); 
        
        return new ResponseEntity<>( targetLocation, HttpStatus.CREATED ); // returns the local relative path in ResponseEntity. 
    } 

    @PostMapping("/uploadFiles") 
    //public List<UploadFileResponse> uploadMultipleFiles( @RequestParam("files") MultipartFile [] files ){ 
    public ResponseEntity<?> uploadMultipleFiles( @RequestParam("files") MultipartFile [] files ){ 
        return new ResponseEntity<>( 
                Arrays.asList( files )
                        .stream()
                        .map( file -> uploadFile( file ) ) 
                        .collect( Collectors.toList() ), HttpStatus.CREATED ); 
    } 

    public String uploadFileLocation( @RequestParam("file") MultipartFile file ){ 
        String targetLocation = this.uploadFile( file ).toString(); 
        return targetLocation
                  .substring( targetLocation.indexOf(",")+1, targetLocation.length()-4 ); // returns the local relative path in String format.  
    } 

    public String[] uploadMultipleFiles( @RequestParam("files") MultipartFile [] files ){ 
        return Arrays.asList( files )
                     .stream()
                     .map( file -> uploadFileLocation( file ) ) 
                     .collect( Collectors.toList() ); 
    } 

} 

