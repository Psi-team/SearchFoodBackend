package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; 
import javax.validation.constraints.Digits; 
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Max; 

//@Component 
public class StoreComments{ 
    
    @NotNull 
    @Size( min = 1, message="The No. of storeId cannot smaller than 1.") 
    private int storeId; 

    @NotNull 
    private String username; 

    @NotNull 
    @Size( min = 1, max = 5, message="The rate must be between 1 and 5.") 
    @Digits( integer=1, fraction=0, message="The rate must be an integer.") 
    private int star; 
    
    @NotNull 
    @Size( min = 1, message="Price cannot be smaller than 1." ) 
    @Digits( integer=5, fraction=0, message="Price must be an integer." ) 
    private int price; 

    private String comment_time; 

    @NotNull 
    @Size( max = 50, message="The words must be smaller than 50 words.") 
    private String comments; 

    private String pic_url; 


    public StoreComments(){ 

    } 

    // setter 
    public void setStoreId( int storeid ){ 
        this.storeId = storeid; 
    } 

    public void setUsername( String usrname ){ 
        this.username = usrname; 
    } 

    public void setStar( int rate ){ 
        this.star = rate; 
    } 

    public void setPirce( int price ){ 
        this.price = price; 
    }

    public void setCommentTime( String cmt ){ 
        this.comment_time = cmt; 
    } 

    public void setComments( String cmts ){ 
        this.comments = cmts; 
    } 

    public void setPicURL( String url ){ 
        this.pic_url = url; 
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

    public int getPirce(){ 
        return this.price; 
    }

    public String getCommentTime(){ 
        return this.comment_time; 
    } 

    public String getComments(){ 
        return this.comments; 
    } 

    public String getPicURL(){ 
        return this.pic_url; 
    } 
    
    

} 

