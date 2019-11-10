package com.searchfood.SearchFoodBackend.model.data; 

import java.util.Map; 
import java.io.Serializable; 
import com.fasterxml.jackson.annotation.JsonProperty;  
import org.json.JSONObject; 

public class Location{ 

    private String latitude; 
    private String longtitude; 

    public Location(){ 

    } 

    public Location( String lat, String lon ){ 
        this.latitude = lat; 
        this.longtitude = lon; 
    } 

    // setter 
    /* 
    @JsonProperty("lat_long") 
    public void setLatitude( Map<String,String> lat_long ){ 
        this.latitude = lat_long.get("lat"); 
    } 

    @JsonProperty("lat_long") 
    public void setLongtitude( Map<String,String> lat_long ){ 
        this.longtitude = lat_long.get("long"); 
    } 
    */ 

    public void setLatitude( String l ){ 
        this.latitude = l; 
    } 

    public void setLongtitude( String l ){ 
        this.longtitude = l;  
    } 

    // getter 
    public String getLatitude(){ 
        return this.latitude; 
    } 

    public String getLongtitude(){ 
        return this.longtitude; 
    } 

    // others 
    /* 
    @JsonProperty("lat_long") 
    public  void unpackNested( Map<String,String> lat_long ){ 
        this.latitude = lat_long.get("lat"); 
        this.longtitude = lat_long.get("long"); 
    } 
    */ 

    public JSONObject getJson(){ 
        JSONObject json = new JSONObject(); 
        json.put("lat",latitude); 
        json.put("long",longtitude); 
        return json; 
    } 
    
    public String getJsonString(){ 
        return this.getJson().toString(); 
    } 

    @Override 
    public String toString() { 
        return "Location: latitude: " + this.latitude + " longtitude: " + this.longtitude + "\n"; 
    } 

} 

