package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class TokenNotFoundException extends RuntimeException{ // data not founded in database. 

    private String message; 

    public TokenNotFoundException(){ 
        super(); 
    } 

    public TokenNotFoundException( String messages ){ 
        super(messages); 
        this.message = messages; 
    } 

    @Override 
    public String getMessage(){ 
        return this.message; 
    } 

    public void setMessage( String m ){ 
        this.message = m; 
    } 

} 


