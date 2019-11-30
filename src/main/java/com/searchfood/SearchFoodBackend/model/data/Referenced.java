package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 

@Component 
public class Referenced{ 

    private int id; // primary key 
    private String value; // the refernced value 
    private String types; // the types. 
    private String class_; 

    // constructors 
    public Referenced(){ 

    } 

    public Referenced( int id, String v, String t, String c ){ 
        this.id = id; 
        this.value = v; 
        this.types = t; 
        this.class_ = c; 
    } 

    // setter 
    public void setId( int id ){ 
        this.id = id; 
    } 

    public void setValue( String d ){ 
        this.value = d; 
    } 

    public void setTypes( String t ){ 
        this.types = t; 
    } 
    
    public void setClass_( String c ){ 
        this.class_ = c; 
    } 

    // getter 
    public int getId(){ 
        return this.id; 
    } 

    public String getValue(){ 
        return this.value; 
    } 

    public String getTypes(){ 
        return this.types; 
    } 

    public String getClass_(){ 
        return this.class_; 
    } 

} 


