package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class DataFailToSavedException extends RuntimeException{ 
    
    private String message; 

    public DataFailToSavedException(){ 
        super(); 
    } 

    public DataFailToSavedException( String m ){ 
        super(m); 
        this.message = m; 
    } 

    // setter 
    public void setMessage( String s ){ 
        this.message = s; 
    } 

    // getter 
    public String getMessage(){ 
        return this.message; 
    } 
} 

