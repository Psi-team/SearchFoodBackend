package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 

@Component 
public class FoodTypes{ 

    private int foodId; 
    private String details; 
    private String types; 

    // constructors 
    public FoodTypes(){ 

    } 

    public FoodTypes( int id, String d, String t ){ 
        this.foodId = id; 
        this.details = d; 
        this.types = t; 
    } 

    // setter 
    public void setFoodId( int id ){ 
        this.foodId = id; 
    } 

    public void setDetails( String d ){ 
        this.details = d; 
    } 

    public void setTypes( String t ){ 
        this.types = t; 
    } 

    // getter 
    public int getFoodId(){ 
        return this.foodId; 
    } 

    public String getDetails(){ 
        return this.details; 
    } 

    public String getTypes(){ 
        return this.types; 
    } 

} 


