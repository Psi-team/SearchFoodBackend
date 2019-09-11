package com.searchfood.SearchFoodBackend.model.data; 

import com.searchfood.SearchFoodBackend.utils.UUIDGenerator; 

public class Members{ 

    private String mail; 
    private String passwd; 
    private String token; 

    public Members(){ 

    } 

    public void setMail( String mail ){ 
        this.mail = mail; 
    } 

    public void setPasswd( String pass ){ 
        this.passwd = pass; 
    }  

    public void setToken(){ 
        UUIDGenerator token_ = new UUIDGenerator( this.mail, this.passwd ); 
        this.token = token_.getUUID(); 
    } 

    public String getMail(){ 
        return this.mail; 
    } 

    public String getPasswd(){ 
        return this.passwd; 
    }  

    public String getToken(){ 
        return this.token; 
    } 

} 

