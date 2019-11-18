package com.searchfood.SearchFoodBackend.model.data; 
import org.springframework.stereotype.Component; 

@Component 
public class Members{ 

    private String username; 
    private String passwd; 
    private String browser; 

    public Members(){ 

    } 

    public Members( String username, String passwd, String brow ){ 
        this.username = username; 
        this.passwd = passwd; 
        this.browser = brow; 
    } 

    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setPasswd( String pass ){ 
        this.passwd = pass; 
    } 

    public void setBrowser( String brow ){ 
        this.browser = brow; 
    } 

    public String getUsername(){ 
        return this.username; 
    } 

    public String getPasswd(){ 
        return this.passwd; 
    } 

    public String getBrowser(){ 
        return this.browser; 
    } 

} 

