package com.searchfood.SearchFoodBackend.model.data; 

import java.util.Map; 
import java.io.Serializable; 
import com.fasterxml.jackson.annotation.JsonProperty; 
import org.json.JSONObject; 

public class FoodTypes{ 

    private String rices; 
    private String noodles; 

    public FoodTypes(){}; 

    public FoodTypes( String rices, String noodles ){ 
        this.rices = rices; 
        this.noodles = noodles; 
    } 

    // setter 
    public void setRices(String rices){ 
        this.rices = rices; 
    } 
    
    public void setNoodles(String noodles){ 
        this.noodles = noodles; 
    } 

    // getter
    public String getRices(){ 
        return this.rices; 
    } 

    public String getNoodles(){ 
        return this.noodles; 
    } 

    public JSONObject getJson(){ 
        // This getter is for getting the org.json.JSONObject 
        JSONObject json = new JSONObject(); 
        json.put("rices",this.rices); 
        json.put("noodles",this.noodles); 
        return json; 
    } 
    
    public String getJsonString(){ 
        // This getter is for getting the org.json.JSONObject in order to write data to MySQL.  
        return this.getJson().toString(); 
    } 

    @Override 
    public String toString() { 
        return "FoodTypes: rices: " + this.rices + " noodles: " + this.noodles + "\n"; 
    } 
} 


