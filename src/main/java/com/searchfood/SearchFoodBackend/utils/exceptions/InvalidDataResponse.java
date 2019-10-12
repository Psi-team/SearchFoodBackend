package com.searchfood.SearchFoodBackend.utils.exceptions; 

public class InvalidDataResponse{ 

    private String fieldName; 
    private String message; 

    public InvalidDataResponse(){ 

    } 

    public InvalidDataResponse( String o, String d ){ 
        this.fieldName = o; 
        this.message = d; 
    } 

    public String getFieldName(){ 
        return this.fieldName; 
    } 

    public String getMessage(){ 
        return this.message; 
    } 

    public void setFieldName( String o ){ 
        this.fieldName = o; 
    } 

    public void setMessage( String d ){ 
        this.message = d; 
    } 

} 

