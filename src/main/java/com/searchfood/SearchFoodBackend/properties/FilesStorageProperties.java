package com.searchfood.SearchFoodBackend.properties; 

import org.springframework.boot.context.properties.ConfigurationProperties; 

@ConfigurationProperties(prefix="file") 
public class FileStorageProperties{ 

    private String uploadDir; 

    public void setUploadDir( String ud ){ 
        this.uploadDir = ud; 
    } 

    public String getUploadDir(){ 
        return this.uploadDir; 
    } 

} 

