package com.searchfood.SearchFoodBackend.webapi; 

// Annotation 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 

// stereotype 
import org.springframework.beans.factory.annotation.Autowired; 
// http
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.Logout; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="logout",produces="application/json") 
public class LogoutController{ 

    private Logout logout; 

    @Autowired 
    public LogoutController( Logout l ){ 
        this.logout = l; 
    } 

    @PostMapping(consumes="application/json")  
    @ResponseStatus( HttpStatus.NO_CONTENT ) 
    public void logout( @RequestBody TokenRecords token ){ 
        // 這裡不能使用TokenRecords接受,因為這樣會把前端傳來的token重新設定 
        // @RequestBody接受到前端的參數並轉成TokenRecords型態,意味著Container會用前端的json中的資訊初始化產生TokenRecords物件
        // 此時前端傳來的只有token沒有username,因此將username設為null,token本身有資料就以setToken(String)來設定token
        // 因此在com.searchfood.SearchFoodBackend.model.Logout印出來的資訊
        // username = null 而 token則為<舊的token> concate <新的token> 
        // 成員函數接受到參數與C++的觀念一樣都會進行local variables初始化,物件會找到相對應的建構函數或方法以初始化
        //
        // 簡單說,setter overloading會違反Java Bean原則, 應避免使用
        // 
        // 解決方法: 
        //  1. 把TokenRecords.java中的setToken( String )刪掉, avoiding setter overloading.  
        //  2. 把setToken(String)的函數名稱改掉,以避免Container不知道要用哪一個setter
        System.out.println( token.getToken() ); 
        if ( 1 != logout.deleteFromToken( token ) ){ 
            throw new NotFoundException( "Token not founded in Database." ); 
        }
    } 
    

} 

