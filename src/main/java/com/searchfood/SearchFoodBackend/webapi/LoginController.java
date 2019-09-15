package com.searchfood.SearchFoodBackend.webapi; 

// Annotation 
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody; // can be omitted if annotating RestController.  
// beans 
import org.springframework.beans.factory.annotation.Autowired; 

// http abstraction 
import org.springframework.http.HttpStatus; 
// user-define class  
import com.searchfood.SearchFoodBackend.model.data.Members; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.TokenRecordsImp; 

@RestController // handle the Restful api 
@CrossOrigin("*") // allows clients from any domain to consume the API.  
@RequestMapping( value="login", produces="application/json" ) // handle the HTTP request /login 
public class LoginController{ 

    /* This class is for checking whether the login user is valid member from the table User in DB.
     * If it is valid member, then return an unique token for HTTP response, or redirect to register page. 
     */ 
    
    @Autowired 
    private TokenRecordsImp tokenImp; 

    public LoginController(){ 
        System.out.println( "***** Construct class sucessfully!   *******" ); 
    } 

    // handle the POST method from url /login/ 
    @PostMapping( value="/", consumes="application/json" ) // receive the json type data.  
    @ResponseStatus( HttpStatus.OK ) // return the http status code. 
    public TokenRecords SignIn( @RequestBody Members member ){ // @RequestBody: the body of request should be convert to Members to parameters. 

        System.out.println("TESTING:\nusername: "+member.getUsername() + "\npassword: "+member.getPassword() ); 

        TokenRecords token = tokenImp.saveTokenTable( member ); 
        // Check whether a member or not, if yes, setUsername and setToken then return a TokenRecords Object. 

        return token; 
    } 

} 


