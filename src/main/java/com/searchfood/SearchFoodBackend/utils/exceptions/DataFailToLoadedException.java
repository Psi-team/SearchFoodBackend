package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class DataFailToLoadedException extends RuntimeException{ 
    
    private String message; 

    public DataFailToLoadedException(){ 
        super(); 
    } 

    public DataFailToLoadedException( String m ){ 
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

