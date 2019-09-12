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
// plain java packages 
import java.util.Map; 
import java.util.HashMap; 

@RestController // handle the Restful api 
@CrossOrigin("*") // allows clients from any domain to consume the API.  
@RequestMapping( value="login", produces="application/json" ) // handle the HTTP request /login 
public class LoginController{ 

    /* This class is for checking whether the login user is valid member from the table User in DB.
     * If it is valid member, then return an unique token for HTTP response, or redirect to register page. 
     * 
     * ------------------------
     * But this is in the test phase. Our goal is to recieve the Json from the Web API and return an token including the username and pass 
     * for the HTTP response. 
     */ 

    @Autowired 
    private Members member; 

    public LoginController(){ 
        System.out.println( "***** Construct class sucessfully!   *******" ); 
    } 

    // handle the POST method from url /login 
    @PostMapping( value="/", consumes="application/json" ) // receive the json type data.  
    @ResponseStatus( HttpStatus.OK ) // return the http status code. 
    //public Map<String,String> getRecievedData( Members member ){ // the body of request should be convert to Members to parameters. 
    public Members getRecievedData( Members member ){ // the body of request should be convert to Members to parameters. 

        System.out.println( "****** Call getRecievedData() sucessfully!   ********" ); 

        //member.setToken(); 

        System.out.println("username: "+member.getUsername() + "\npassword: "+member.getPasswd() ); 

        //Map<String, String> target = new HashMap(); 
        //target.put("username",member.getUsername() ); 
        //target.put("token",member.getToken() ); 
        
        return member; 
    } 
} 


