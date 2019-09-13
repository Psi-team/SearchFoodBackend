package com.searchfood.SearchFoodBackend.utils; 

import java.util.UUID; 

public class UUIDGenerator{ 

    private String token; 

    public UUIDGenerator( String usrname ){ 
        this.token = usrname + UUID.randomUUID().toString();  
    } 

    public UUIDGenerator(){ 
        this.token = UUID.randomUUID().toString();  
    } 

    public String getUUID(){ 
        return this.token; 
    } 
} 

