package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; 
import javax.validation.constraints.Digits; 
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Max; 

import java.time.LocalDateTime; 
import java.sql.Timestamp; 

import java.io.Serializable; 
import org.json.JSONObject; 
import com.fasterxml.jackson.annotation.JsonProperty;  
import com.searchfood.SearchFoodBackend.model.data.Location; 
import com.searchfood.SearchFoodBackend.model.data.FoodTypes; 

import java.util.Map; 
import java.util.HashMap; 

@Component 
public class StoreInfo implements Serializable{ 

    //@NotNull 
    private int storeId; 
    
    @NotNull 
    @Size( max=14, message="The length of StoreName must be smaller than 14" ) 
    private String storename; 

    @NotNull 
    @Size(max=3) 
    private String city; 

    @NotNull 
    @Size(max=5) 
    private String district; 

    @NotNull 
    @Size( max=10 ) 
    private String address; 

    @NotNull 
    @Size(max=10) 
    private String tel; 

    //@NotNull 
    private String creator; 
    
    /* 
     * 處理nested Json: 
     *  1. 用Java Bean來封裝該nested Json, 並轉成JSONObject, 但若要存至DB,則必須要實做序列化, 或是用JSONObject.toString()來存入MySQL 
     *  2. 用Map<String,String>來封裝該nested Json, 但會有Cannot create a JSON value from a string with CHARACTER SET 'binary'. 
     */ 
    @NotNull 
    private Location lat_long; //private Map<String,String> lat_long; 
    @NotNull 
    private FoodTypes types; //private Map<String,String> types; 

    private LocalDateTime createdAt; 
    // 可以使用java.util.Date or java.sql.Timestamp or java.time.LocalDateTime, the point is the time_zone of MySQL.  
    // ref: modify the time zone of MySQL. 
    // https://bonvoyagelin.blogspot.com/2011/01/mysql.html?fbclid=IwAR3c6-omIFTqSFThTahQEvwjAM6bPcvI3F2QFUl7quH_ColRrt9NQjLeKEo
    
    private String business_time; 
    private int cleek_week; 
    private int cleek_cum;  
    

    // constructor 
    public StoreInfo(){ 

    } 

    public StoreInfo(String n, String c, String d, String a, String t, String ct, 
                                Location latlong, FoodTypes ty, LocalDateTime cd, String bt ){ 
        this.storename = n; 
        this.city = c; 
        this.district = d; 
        this.address = a; 
        this.tel = t; 
        this.creator = ct; 
        this.lat_long = latlong; 
        this.types = ty; 
        this.createdAt = cd; 
        this.business_time = bt; 
    } 

    // setter 
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

    public void setTel( String tel ){ 
        this.tel = tel; 
    } 

    public void setCreator( String catr ){ 
        this.creator = catr; 
    } 

    public void setCreatedAt( LocalDateTime date ){ 
        // this.createdAt = Timestamp.valueOf( date ); // converting the date from java.time.LocalDateTime to java.sql.Timestamp format. 
        this.createdAt = date; 
    } 

    public void setBusinessTime( String time ){ 
        this.business_time = time; 
    } 

    //public void setLat_long( Map<String,String> loc ){ 
    public void setLat_long( Location loc ){ 
        this.lat_long = loc; 
    } 

    //public void setTypes( Map<String,String> type ){ 
    public void setTypes( FoodTypes type ){ 
        System.out.println("TYPE: " + type); 
        this.types = type; 
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

    public String getTel(){ 
        return this.tel; 
    } 

    public String getCreator(){ 
        return this.creator; 
    } 

    public LocalDateTime getCreatedAt(){ 
        return this.createdAt; 
    } 

    public String getBusinessTime(){ 
        return this.business_time; 
    } 

    public Map<String,String> getLat_long(){ 
        Map<String,String> map = new HashMap(); 
        map.put("lat",this.lat_long.getLatitude()); 
        map.put("long",this.lat_long.getLongtitude()); 
        return map;  
    } 

    public Map<String,String> getTypes(){ 
        Map<String,String> map = new HashMap(); 
        map.put("rices",this.types.getRices()); 
        return map;  
    } 


    /* JsonXXX() below is in order to get the String type of JSONObject to write in MySQL, whose name cannot be getXXX. 
     * Becuase the container will view getXXX as getter then converts it to the JSON data of Http response. */ 
    public String JsonLocation(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return this.lat_long.getJsonString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 

    public String JsonTypes(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return this.types.getJsonString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 

} 

