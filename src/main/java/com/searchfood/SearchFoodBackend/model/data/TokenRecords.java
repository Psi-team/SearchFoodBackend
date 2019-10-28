package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

import com.searchfood.SearchFoodBackend.utils.UUIDGenerator; 

// a class for token record 
@Component 
public class TokenRecords{ 

    private String username; 
    private String token; 
    //private String browser; 

    public TokenRecords(){ 

    } 

    public TokenRecords(String s, String t){ 
        this.username = s; 
        this.token = t; 
    } 

    public void setUsername( String usrname ){ 
        this.username = usrname; 
    } 

    // 簡單說,setter overloading會違反Java Bean原則, 應避免使用
    // 
    // 解決方法: 
    //  1. 把TokenRecords.java中的setToken( String )刪掉, avoiding setter overloading.  
    //  2. 把setToken(String)的函數名稱改掉,以避免Container不知道要用哪一個setter
    public void setTokenByUsername( String username ){ 
        UUIDGenerator tk = new  UUIDGenerator( username ); 
        //System.out.println("Setting token with username"); 
        this.token = tk.getUUID(); 
    } 

    public void setToken(){ 
        UUIDGenerator tk = new UUIDGenerator(); 
        //System.out.println("Setting token with no params"); 
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


