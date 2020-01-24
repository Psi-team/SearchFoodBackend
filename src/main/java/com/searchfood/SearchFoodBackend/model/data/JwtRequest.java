package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.annotation.Component; 

@Component 
public JwtRequest{ 

    private String username; 
    private String password; 

    public JwtRequest(){ 

    } 

    public void setUsername( String username ){ 
        this.username = username; 
    } 

    public void setPassword( String password ){ 
        this.password = password; 
    } 

    public String getUsername(){ 
        return this.username; 
    } 

    public String getPassword(){ 
        return this.password; 
    } 

} 

