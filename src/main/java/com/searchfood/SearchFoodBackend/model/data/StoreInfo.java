package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; 
import javax.validation.constraints.NotNull; 

import org.springframework.web.multipart.MultipartFile; 

import java.time.LocalDateTime; 
import java.sql.Timestamp; 

import java.io.Serializable; 
import org.json.JSONObject; 
import com.fasterxml.jackson.annotation.JsonProperty;  

import java.util.Map; 
import java.util.List; 
import java.util.ArrayList; 
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
    private Map<String,Integer> latLong; // private Location latlong; 
    @NotNull 
    private Map<String,List<String>> type; 
    private Map<String,String> businessHours; 

    private Timestamp createdAt; 
    // 可以使用java.util.Date or java.sql.Timestamp or java.time.LocalDateTime, the point is the time_zone of MySQL.  
    // ref: modify the time zone of MySQL. 
    // https://bonvoyagelin.blogspot.com/2011/01/mysql.html?fbclid=IwAR3c6-omIFTqSFThTahQEvwjAM6bPcvI3F2QFUl7quH_ColRrt9NQjLeKEo
    
    private int cleek_week; 
    private int cleek_cum;  
    private float rating; 

    private MultipartFile logo; 
    private MultipartFile images; 
    private String slogan; 
    private List<String> tags; 
    
    // constructor 
    public StoreInfo(){ 

    } 

    public StoreInfo(String n, String c, String d, String a, String t, String ct, 
                                Map<String,Integer> latlong, Map<String,List<String>> ty, Timestamp cd, Map<String,String> bt, float rating, 
                                MultipartFile logo, MultipartFile images, String slogan, List<String> tags){ 
        this.storename = n; 
        this.city = c; 
        this.district = d; 
        this.address = a; 
        this.tel = t; 
        this.creator = ct; 
        this.latLong = latlong; 
        this.type = ty; 
        this.createdAt = cd; 
        this.businessHours = bt; 
        this.rating = rating; 
        this.logo = logo; 
        this.images = images; 
        this.slogan = slogan; 
        this.tags = tags; 
    } 

    /* 
    public StoreInfo( int id, String n, String c, String d, String a, String t, String ct, 
                                Map<String,Integer> latlong, Map<String,List<String>> ty, Timestamp cd, Map<String,String> bt, float rating ){ 
        this.storeId = id; 
        this.storename = n; 
        this.city = c; 
        this.district = d; 
        this.address = a; 
        this.tel = t; 
        this.creator = ct; 
        this.latLong = latlong; 
        this.type = ty; 
        this.createdAt = cd; 
        this.businessHours = bt; 
        this.rating = rating; 
    } 
    */ 

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

    public void setLatLong( Map<String,Integer> loc ){ 
        this.latLong = loc; 
    } 

    public void setType( Map<String,List<String>> type ){ 
        this.type = type; 
    } 

    public void setRating( float rating ){ 
        this.rating = rating; 
    } 

    public void setSlogan( String s ){ 
        this.slogan = s; 
    } 

    public void setLogo( MultipartFile s ){ 
        this.logo = s; 
    } 

    public void setImages( MultipartFile s ){ 
        this.images = s; 
    } 

    public void setTags( List<String> t ){ 
        this.tags = t; 
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

    public Map<String,Integer> getLatLong(){ 
        return this.latLong;  
    } 

    public Map<String,List<String>> getType(){ 
        return this.type; 
    } 

    public float getRating(){ 
        return this.rating; 
    } 

    public MultipartFile getLogo(){ 
        return this.logo; 
    } 

    public MultipartFile getImages(){ 
        return this.images; 
    } 

    public String getSlogan(){ 
        return this.slogan; 
    } 

    public List<String> getTags(){ 
        return this.tags; 
    } 

    /* JsonXXX() below is in order to get the String type of JSONObject to write in MySQL, whose name cannot be getXXX. 
     * Becuase the container will view getXXX as getter then converts it to the JSON data of Http response. */ 
    public JSONObject JsonLatLong(){ 
       return new JSONObject( this.latLong ); 
    } 
    public String JsonLatLongString(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       //return this.latlong.getJsonString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
       return (new JSONObject( this.latLong )).toString(); 
    } 

    public JSONObject JsonType(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return new JSONObject( this.type ); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
    } 
    public String JsonTypesString(){ 
       // this getter is made in order to get the String of JSONObject to store in MySQL.  
       return (new JSONObject( this.type )).toString(); // 必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
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

