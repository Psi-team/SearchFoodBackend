package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 

//@Component 
public class Members{ 

    private String username; 

    private String password; 

    public Members(){ 

    } 

    public Members( String username, String password ){ 
        this.username = username; 
        this.password = password; 
    } 

    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setPasswd( String pass ){ 
        this.password = pass; 
    } 

    public String getUsername(){ 
        return this.username; 
    } 

    public String getPassword(){ 
        return this.password; 
    } 

} 

