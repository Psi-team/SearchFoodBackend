package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 
import org.springframework.web.multipart.MultipartFile; 

import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size; // used for checking String.  
import javax.validation.constraints.Max; 
import javax.validation.constraints.Min; 
import javax.validation.constraints.Digits; 

import java.sql.Timestamp; 

@Component 
public class Comments{ 

    @NotNull 
    private int storeId; 
    private String username; 

    @NotNull 
    @Min(1) 
    @Max(5) 
    @Digits( integer=1,fraction=0, message="The start must between 0 and 1" ) 
    private int star; 

    @Size( max = 50, message = "The words must be smaller than 50 words." ) 
    private String comments; 

    private String price; 
    private Timestamp commentAt;  
    //private MultipartFile pic; 

    public Comments(){ 

    } 

    // setter 
    public void setStoreId( int storeId ){ 
        this.storeId = storeId; 
    } 

    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setStar( int star ){ 
        this.star = star; 
    } 

    public void setComments( String comments ){ 
        this.comments = comments; 
    } 

    public void setPrice( String price ){ 
        this.price = price; 
    } 

    //public void setPic( MultipartFile pic ){ 
    //    this.pic = pic; 
    //} 
    public void setCommentAt( Timestamp cA ){ 
        this.commentAt = cA; 
    } 

    // getter 
    public int getStoreId(){ 
        return this.storeId; 
    } 

    public String getUsername(){ 
        return this.username; 
    } 

    public int getStar(){ 
        return this.star; 
    } 

    public String getComments(){ 
        return this.comments; 
    } 

    public String getPrice(){ 
        return this.price; 
    } 

    //public MultipartFile getPic(){ 
    //    return this.pic; 
    //} 
    public Timestamp getCommentAt(){ 
        return this.commentAt; 
    } 

} 

