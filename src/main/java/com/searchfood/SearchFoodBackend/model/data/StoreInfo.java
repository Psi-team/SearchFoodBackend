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

import java.util.Map; 
import java.util.List; 
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
    @Size(min=9,max=10) 
    private String tel; 

    //@NotNull 
    private String creator; 
    
    /* 
     * 處理nested Json: 
     *  1. 用Java Bean來封裝該nested Json, 並轉成JSONObject, 但若要存至DB,則必須要實做序列化, 或是用JSONObject.toString()來存入MySQL 
     *  2. 用Map<String,String>來封裝該nested Json, 但會有Cannot create a JSON value from a string with CHARACTER SET 'binary'. 
     *  在此的作法是用Map來裝nested Json, 並且透過定義成員函數將Map轉換成JSONObject並使用.toString()將其寫入資料庫
     */ 
    @NotNull 
    private Map<String,Integer> latlong; // private Location latlong; 
    @NotNull 
    private Map<String,List<String>> types; 
    private Map<String,String> businessHours; 

    private Timestamp createdAt; 
    // 可以使用java.util.Date or java.sql.Timestamp or java.time.LocalDateTime, the point is the time_zone of MySQL.  
    // ref: modify the time zone of MySQL. 
    // https://bonvoyagelin.blogspot.com/2011/01/mysql.html?fbclid=IwAR3c6-omIFTqSFThTahQEvwjAM6bPcvI3F2QFUl7quH_ColRrt9NQjLeKEo
    
    private int cleek_week; 
    private int cleek_cum;  
    
    // constructor 
    public StoreInfo(){ 

    } 

    public StoreInfo(String n, String c, String d, String a, String t, String ct, 
                                Map<String,Integer> latlong, Map<String,List<String>> ty, Timestamp cd, Map<String,String> bt ){ 
        this.storename = n; 
        this.city = c; 
        this.district = d; 
        this.address = a; 
        this.tel = t; 
        this.creator = ct; 
        this.latlong = latlong; 
        this.types = ty; 
        this.createdAt = cd; 
        this.businessHours = bt; 
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
        this.createdAt = Timestamp.valueOf( date ); // converting the date from java.time.LocalDateTime to java.sql.Timestamp format. 
    } 

    public void setBusinessHours( Map<String,String> time ){ 
        this.businessHours = time; 
    } 

    public void setLatlong( Map<String,Integer> loc ){ 
        this.latlong = loc; 
    } 

    public void setTypes( Map<String,List<String>> type ){ 
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

    public Timestamp getCreatedAt(){ 
        return this.createdAt; 
    } 

    public Map<String,String> getBusinessHours(){ 
        return this.businessHours; 
    } 

    public Map<String,Integer> getLatlong(){ 
        return this.latlong;  
    } 

    public Map<String,List<String>> getTypes(){ 
        return this.types;  
    } 


    /* JsonXXX() below is in order to get the String type of JSONObject to write in MySQL, whose name cannot be getXXX. 
     * Becuase the container will view getXXX as getter then converts it to the JSON data of Http response. */ 
    public JSONObject JsonLatLong(){ 
       return new JSONObject( this.latlong ); 
    } 
    public String JsonLatLongString(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       //return this.latlong.getJsonString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
       return (new JSONObject( this.latlong )).toString(); 
    } 

    public JSONObject JsonTypes(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return new JSONObject( this.types ); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 
    public String JsonTypesString(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return (new JSONObject( this.types )).toString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 

    public JSONObject JsonBusinessHours(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return new JSONObject( this.businessHours ); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 
    public String JsonBusinessHoursString(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return (new JSONObject( this.businessHours )).toString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 

} 

