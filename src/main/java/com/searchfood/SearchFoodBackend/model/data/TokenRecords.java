package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

import com.searchfood.SearchFoodBackend.utils.UUIDGenerator; 

// a class for token record 
//@Component 
public class TokenRecords{ 

    private String username; 
    private String token; 
    //private String browser; 

    public TokenRecords(){ 

    } 

    public void setUsername( String usrname ){ 
        this.username = usrname; 
    } 

    public void setToken( String username ){ 
        UUIDGenerator tk = new  UUIDGenerator( username ); 
        this.token = tk.getUUID(); 
    } 

    public void setToken(){ 
        UUIDGenerator tk = new UUIDGenerator(); 
        this.token = tk.getUUID(); 
    } 

    //public void setBrowser( String brow ){ 
    //    this.browser = brow; 
    //} 

    public String getUsername(){ 
        return username; 
    } 

    public String getToken(){ 
        return token; 
    } 

    //public String getBrowser(){ 
    //    return this.browser; 
    //} 

} 


