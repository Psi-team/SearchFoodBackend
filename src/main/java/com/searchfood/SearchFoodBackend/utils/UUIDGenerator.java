package com.searchfood.SearchFoodBackend.utils; 

import java.util.UUID; 

public class UUIDGenerator{ 

    private token; 

    public UUIDGenerator( String str, String str2 ){ 
        this.token = str+str2+ UUID.randomUUID().toString();  
    } 

    public UUIDGenerator(){ 
        this.token = UUID.randomUUID().toString();  
    } 

    public String getUUID(){ 
        return this.token; 
    } 
} 

