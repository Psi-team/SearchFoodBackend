package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class InvalidDataException{ 

    private String message; 
    private Object errors; 

    public InvalidDataException(){ 
        super(); 
    } 

    public InvalidDataException( String m, Object e ){ 
        this.message = m;
        this.errors = e; 
    } 

    public String getMessage(){ 
        return this.message; 
    } 

    public Object getErrors(){ 
        return this.errors; 
    } 

    public void setMessage( String m ){ 
        this.message = m; 
    } 

    public void setErrors( Object e ){ 
        this.errors = e; 
    } 

} 

