package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class TokenExpiredException extends RuntimeException{ 

    private String message; 

    public TokenExpiredException(){ 
        super(); 
    } 

    public TokenExpiredException( String m ){ 
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

