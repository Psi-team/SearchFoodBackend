package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 

import com.searchfood.SearchFoodBackend.utils.UUIDGenerator; 

@Component 
public class Members{ 

    private String username; 
    private String passwd; 
    //private String token; 

    public Members(){ 
        System.out.println( "*********  Construct Members object    ***********"); 
    } 

    public void setUsername( String username ){ 
        this.username = username; 
    } 

    public void setPasswd( String pass ){ 
        this.passwd = pass; 
    }  

    //public void setToken(){ 
    //    UUIDGenerator token_ = new UUIDGenerator(); 
    //    this.token = token_.getUUID(); 
    //} 

    public String getUsername(){ 
        return this.username; 
    } 

    public String getPasswd(){ 
        return this.passwd; 
    }  

    //public String getToken(){ 
    //    return this.token; 
    //} 

} 

