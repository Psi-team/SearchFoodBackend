package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class DataExistException extends RuntimeException{ 

    private String message; 

    public DataExistException(){ 
        super(); 
    } 

    public DataExistException( String m ){ 
        super(m); 
        this.message = m; 
    } 

    @Override 
    public String getMessage(){ 
        return this.message; 
    } 

    public void setMessage( String m ){ 
        this.message = m; 
    } 

} 

