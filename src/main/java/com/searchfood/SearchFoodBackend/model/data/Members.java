package com.searchfood.SearchFoodBackend.model.data; 
import org.springframework.stereotype.Component; 

@Component 
public class Members{ 

    private String mail; 
    private String passwd; 
    private String browser; 

    public Members(){ 

    } 

    public Members( String mail, String passwd, String brow ){ 
        this.mail = mail; 
        this.passwd = passwd; 
        this.browser = brow; 
    } 

    public void setMail( String name ){ 
        this.mail = name; 
    } 

    public void setPasswd( String pass ){ 
        this.passwd = pass; 
    } 

    public void setBrowser( String brow ){ 
        this.browser = brow; 
    } 

    public String getMail(){ 
        return this.mail; 
    } 

    public String getPasswd(){ 
        return this.passwd; 
    } 

    public String getBrowser(){ 
        return this.browser; 
    } 

} 

