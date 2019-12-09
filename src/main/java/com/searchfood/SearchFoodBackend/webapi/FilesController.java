package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.springframework.web.multipart.MultipartFile; 
import org.springframework.web.servlet.support.ServeletUriComponentsBuilder; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.files.FileStorageService; 
import com.searchfood.SearchFoodBackend.utils.files.UploadFileResponse; 

@RestController 
public FilesController{ 

    private static final Logger log = LoggerFactory.getLogger( FilesController.class ); 

    @Autowired 
    private FileStorageService fileStorageService; 

    @PostMapping("/uploadFile") 
    public ResponseEntity<?> uploadFile( @RequestParam("file") MultipartFile file ){ 

        String fileName = fileStorageService.storeFile( file ); 

        if ( StringUtils.isEmpty( fileName ) throw new FileException("Please choose a images"); 

        String fileDownloadURI = 
                             ServeletUriComponentsBuilder
                                    .fromCurrentContextPath()
                                    .path("/downloadFile/") 
                                    .toUriString(); 

        return new ResponseEntity<>( 
                    new UploadFileResponse( file, fileDownloadURI, 
                        file.getContentType(), file.getSize() ), 
                    HttpStatus.CREATED ); 
    } 

    @PostMapping("/uploadFiles") 
    public List<UploadResponse> uploadMultipleFiles( @RequestParam("files") MultipartFile [] files ){ 
        return Arrays.asList( files )
                        .stream()
                        .map( file -> uploadFile( file ) ) 
                        .collect( Collectors.toList() ); 
    } 

} 

