package com.searchfood.SearchFoodBackend.webapi; 

// Annotations 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RequestBody; 
// Autowired 
import org.springframework.beans.factory.annotation.Autowired; 
// a basic abstraction over client/server-side HTTP. 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
// Validation  
import javax.validation.Valid; // Valid whether the bean meets constraints. 
import org.springframework.validation.Errors; // If constraints don't meet, capture the error messages. 
// User-defined class 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.model.SignUpMemberImp; 

import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.messages.interfaces.SignUpMailService; 

import java.util.List; 
import java.util.LinkedList; 

@RestController 
@RequestMapping( value="signup", produces="application/json" ) 
@CrossOrigin("*") 
public class SignUpController{ 

    private SignUpMemberImp signupImp;  
    private TokenRecords token; 
    private SignUpMailService signupMailService; 

    @Autowired 
    public SignUpController( SignUpMemberImp signupImp, TokenRecords token, SignUpMailService signupMailService ){ 
        this.signupImp = signupImp; 
        this.token = token; 
        this.signupMailService = signupMailService; 
    } 
    
    @PostMapping( consumes="application/json" ) 
    public ResponseEntity<?> signUp( @Valid @RequestBody SignUpMember signupmember, 
                                Errors errors ){ 

        System.out.println("TESTING:\n username: " + signupmember.getUsername() + " password: " + signupmember.getPasswd() + 
                            " birthyear: " + signupmember.getBirthyear() + " sex: " + signupmember.getSexual() ); 
        
        if( errors.hasErrors() ){ 
            throw new InvalidDataException( errors ); 
        } 

        token = signupImp.saveToUsers( signupmember ); 
        if ( token.getToken() == null ){ 
            throw new DataExistException("The mail has existed."); 
        } 

        // then send mail to new member. 
        signupMailService.sendEmail( signupmember ); 
        
        return new ResponseEntity<>( token,HttpStatus.CREATED ); 

    } 

} 

