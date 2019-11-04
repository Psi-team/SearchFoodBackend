package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; 
import javax.validation.constraints.Digits; 
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Max; 
import java.util.Date; 

@Component 
public class StoreInfo{ 

    //@NotNull 
    //private int storeId; 
    
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
    
    @NotNull 
    private String lat_long; 

    @NotNull 
    private String types; 

    private Date created_date; 
    private String business_time; 
    //private int cleek_week; 
    //private int cleek_cum;  
    

    // constructor 
    public StoreInfo(){ 

    } 

    // setter 
    //public void setStoreId( int id ){ 
    //    this.storeId = id; 
    //} 

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

    public void setCreatedDate( Date date ){ 
        this.created_date = date; 
    } 

    public void setBusinessTime( String time ){ 
        this.business_time = time; 
    } 

    public void setLat_long( String loc ){ 
        this.lat_long = loc; 
    } 

    public void setTypes( String type ){ 
        this.types = type; 
    } 

    // getter 
    //public int getStoreId( int id ){ 
    //    return this.storeId = id; 
    //} 

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

    public Date getCreatedDate(){ 
        return this.created_date; 
    } 

    public String getBusinessTime(){ 
        return this.business_time; 
    } 

    public String getLat_long(){ 
        return this.lat_long; 
    } 

    public String getTypes(){ 
        return this.types; 
    } 

} 

