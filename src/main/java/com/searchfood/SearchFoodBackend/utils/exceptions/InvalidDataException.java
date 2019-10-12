package com.searchfood.SearchFoodBackend.utils.exceptions; 

import org.springframework.validation.Errors; // If constraints don't meet, capture the error messages. 

public class InvalidDataException extends RuntimeException{ 

    private Object errors; 

    public InvalidDataException(){ 

    } 

    public InvalidDataException( Object e ){ 
        this.errors = e; 
    } 

    public Errors getErrors(){ 
        return (Errors) this.errors; 
    } 

    public void setErrors( Object e ){ 
        this.errors = e; 
    } 

} 

