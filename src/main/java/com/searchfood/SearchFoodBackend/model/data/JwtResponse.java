package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.annotation.Component; 

@Component 
public JwtResponse{ 

    private final String jwtToken; 

    public JwtResponse( String jwtToken ){ 
        this.jwtToken = jwtToken; 
    } 

    public String getJwtToken(){ 
        return this.jwtToken; 
    } 

    public void setJwtToken( String token ){ 
        return this.jwtToken = token; 
    } 

} 

