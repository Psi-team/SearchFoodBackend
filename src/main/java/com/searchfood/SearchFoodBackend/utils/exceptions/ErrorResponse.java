package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class ErrorResponse{ // boxing the error messages. 

    private String message; 

    public ErrorResponse( String m ){ 
        this.message = m; 
    } 

    public String getMessage(){ 
        return this.message; 
    } 

    public void setMessage( String m ){ 
        this.message = m; 
    } 
} 

