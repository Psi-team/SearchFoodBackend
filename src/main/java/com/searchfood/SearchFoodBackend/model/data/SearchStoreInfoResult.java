package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 

import java.util.Map; 
import java.sql.Timestamp; 

@Component 
public class SearchStoreInfoResults{ 

    private int storeId; 
    private String storename; 
    private String city; 
    private String district; 
    private String address; 
    private float rating; 
    private Timestamp createdAt; 
    private Map<String, String> businessHours; 
    private Map<String,String> latLong; 

    // constructor 
    public SearchStoreInfoResults(){ 

    } 

    public SearchStoreInfoResults( 
            int id, String n, String c, String d, String a, float r, Timestamp ca, Map<String,String> b, Map<String,String> l ){ 
        this.storeId = id; 
        this.storename = n; 
        this.city = c; 
        this.district = d; 
        this.address = a; 
        this.rating = r; 
        this.createdAt = ca; 
        this.businessHours = b; 
        this.latlong = l; 
    } 

    //setter 
    public void setStoreId( int id ){ 
        this.storeId = id; 
    } 

    public void setStorename( String name ){ 
        this.storename = name; 
    } 

    public void setCity( String city ){ 
        this.city = city; 
    } 

    public void setDistrict( String dist ){ 
        this.district = dist; 
    } 

    public void setAddress( String addr ){ 
        this.address = addr; 
    } 

    public void setCreatedAt( LocalDateTime date ){ 
        this.createdAt = Timestamp.valueOf( date ); // converting the date from java.time.LocalDateTime to java.sql.Timestamp format. 
    } 

    public void setBusinessHours( Map<String,String> time ){ 
        this.businessHours = time; 
    } 

    public void setLatLong( Map<String,Integer> loc ){ 
        this.latLong = loc; 
    } 

    // getter 
    public int getStoreId( int id ){ 
        return this.storeId = id; 
    } 

    public String getStorename(){ 
        return this.storename; 
    } 

    public String getCity(){ 
        return this.city; 
    } 

    public String getDistrict(){ 
        return this.district; 
    } 

    public String getAddress(){ 
        return this.address; 
    } 

    public Timestamp getCreatedAt(){ 
        return this.createdAt; 
    } 

    public Map<String,String> getBusinessHours(){ 
        return this.businessHours; 
    } 

    public Map<String,Integer> getLatLong(){ 
        return this.latLong;  
    } 

    public float getRating(){ 
        return this.rating; 
    } 

} 

