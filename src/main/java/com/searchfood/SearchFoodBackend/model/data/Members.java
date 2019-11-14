package com.searchfood.SearchFoodBackend.model.data; 
import org.springframework.stereotype.Component; 

@Component 
public class Members{ 

    private String username; 
    private String password; 
    private String browser; 

    public Members(){ 

    } 

    public Members( String username, String password, String brow ){ 
        this.username = username; 
        this.password = password; 
        this.browser = brow; 
    } 

    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setPassword( String pass ){ 
        this.password = pass; 
    } 

    public void setBrowser( String brow ){ 
        this.browser = brow; 
    } 

    public String getUsername(){ 
        return this.username; 
    } 

    public String getPassword(){ 
        return this.password; 
    } 

    public String getBrowser(){ 
        return this.browser; 
    } 

} 

