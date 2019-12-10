package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class FilesException extends RuntimeException{ 

    private String message; 

    public FilesException(){ 
        super(); 
    } 

    public FilesException( String m ){ 
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


