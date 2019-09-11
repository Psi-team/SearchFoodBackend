package com.searchfood.SearchFoodBackend.web-api; 

// Annotation 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestStatus; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody; 
// http abstraction 
import org.springframework.http.HttpStatus; 
// user-define class  
import com.searchfood.SearchFoodBackend.model.data.Members; 
// plain java packages 
import java.util.Map; 
import java.util.HashMap; 

@RestController // handle the Restful api 
@CrossOrigin // allows clients from any domain to consume the API.  
@RequestMapping( path="/login", produces="application/json" ) // handle the HTTP request /login 
public class LoginController{ 

    /* This class is for checking whether the login user is valid member from the table User in DB.
     * If it is valid member, then return an unique token for HTTP response, or redirect to register page. 
     * 
     * ------------------------
     * But this is in the test phase. Our goal is to recieve the Json from the Web API and return an token including the username and pass 
     * for the HTTP response. 
     */ 

    //@Autowired 
    //private Members member; 

    // handle the POST method from url /login 
    @PostMapping( consume="application/json" ) // receive the json type data.  
    @RequestStatus( HttpStatus.OK ) // return the http status code. 
    public @ResponseBody Map<String,String> getRecievedData( @RequestBody Members member ){ // the body of request should be convert to Members to parameters. 

        Map<String,String> target = new HashMap<>(); 
        String token = member.getToken(); 
        target.put( "username", member.getMail() ); 
        target.put( "token", token ); 
        return target; 
    } 
} 


