package com.searchfood.SearchFoodBackend.webapi; 

// Annotation 
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RequestBody; 
//import org.springframework.web.bind.annotation.ResponseBody; // can be omitted if annotating RestController.  
// beans 
import org.springframework.beans.factory.annotation.Autowired; 

// http abstraction 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

// logging import org.slf4j.Logger; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

// user-define class  
import com.searchfood.SearchFoodBackend.model.data.Members; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.TokenRecordsImp; 

import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

@RestController // handle the Restful api 
@CrossOrigin("*") // allows clients from any domain to consume the API.  
@RequestMapping( value="login", produces="application/json" ) // handle the HTTP request /login 
public class LoginController{ 

    /* This class is for checking whether the login user is valid member from the table User in DB.
     * If it is valid member, then return an unique token for HTTP response, or redirect to register page. 
     */ 
    
    private static final Logger log = LoggerFactory.getLogger( LoginController.class.getName() ); 
    /* slf4j: 
     *      slf4j只提供日誌的接口.提供獲取具體日誌物件的方法,其只是一種日誌標準, 並不是日誌系統的具體實現, 具體實現可以使用java.util.logging, logback等等
     *      只要所有程式碼都使用slf4j當作接口,不論實現日誌實做是什麼框架.最終所有地方使用一種實現方法即可,維護更新接很方便 
     *      https://blog.csdn.net/shiyong1949/article/details/78844342
     */ 

    private TokenRecordsImp tokenImp; 
    private TokenRecords token; 

    @Autowired 
    public LoginController( TokenRecordsImp tokenImp, TokenRecords token ){ 
        this.tokenImp = tokenImp; 
        this.token = token; 
        //System.out.println( "***** Construct class sucessfully! *******" ); 
    } 

    // handle the POST method from url /login 
    @PostMapping( consumes="application/json" ) // receive the json type data.  
    //@ResponseStatus( HttpStatus.OK ) // return the http status code. 
    //public TokenRecords LoginIn( @RequestBody Members member ){ // @RequestBody: the body of request should be convert to Members as parameters. 
    public ResponseEntity<?> login( @RequestBody Members member ){ // @RequestBody: the body of request should be convert to Members as parameters. 

        log.debug( "TESTING logging: username: "+member.getUsername() + "  password: "+member.getPasswd() + " browser: " + member.getBrowser() ); 
        log.info( "username: "+member.getUsername() + ", password: "+member.getPasswd() + "send request to login" ); 

        token = tokenImp.saveTokenTable( member ); 

        if ( null == token.getUsername() ){ 
            log.info("Not Founded"); 
            throw new NotFoundException("User or password not founded."); 
             /* Reference: https://openjry.url.tw/spring-boot-rest-exception-all-catch/ */ 
        } 

        return new ResponseEntity<>( token, HttpStatus.OK ); 
    } 


} 


