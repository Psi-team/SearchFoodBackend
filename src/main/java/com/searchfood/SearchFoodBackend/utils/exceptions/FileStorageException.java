package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class FileStorageException extends RuntimeException{ 

    private String message; 

    public FileStorageException(){ 
        super(); 
    } 

    public FileStorageException( String m ){ 
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


