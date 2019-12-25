package com.searchfood.SearchFoodBackend.model.data; 

import org.springframework.stereotype.Component; 
import javax.validation.constraints.Email; 
import javax.validation.constraints.NotNull; 

@Component 
public class Emails{ 

    @NotNull 
    @Email( message="Invalid email format") 
    private String email; 


    public void setEmail( String e ){ 
        this.email = e; 
    } 

    public String getEmail(){ 
        return this.email; 
    } 

} 

