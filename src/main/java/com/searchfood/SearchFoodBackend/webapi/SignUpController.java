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
import org.springframework.validation.FieldError; 
// User-defined class 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.model.SignUpMemberImp; 

import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 

import java.util.List; 
import java.util.LinkedList; 

@RestController 
@RequestMapping( value="signup", produces="application/json" ) 
@CrossOrigin("*") 
public class SignUpController{ 

    private SignUpMemberImp signupImp;  
    private TokenRecords token; 

    @Autowired 
    public SignUpController( SignUpMemberImp signupImp, TokenRecords token ){ 
        this.signupImp = signupImp; 
        this.token = token; 
    } 
    
    @PostMapping( consumes="application/json" ) 
    public ResponseEntity<?> signUp( @Valid @RequestBody SignUpMember signupmember, 
                                Errors errors ){ 

        System.out.println("TESTING:\n username: " + signupmember.getUsername() + " password: " + signupmember.getPasswd() + 
                            " birthyear: " + signupmember.getBirthyear() + " sex: " + signupmember.getSexual() ); 
        
        if( errors.hasErrors() ){ 
            System.out.println("**TESTING***"); 
            List<FieldError> Errors = errors.getFieldErrors(); 
            for ( FieldError error : Errors ){ 
                System.out.println( error.getObjectName() + " - " + error.getDefaultMessage() );  
            } 

            return new ResponseEntity<>( errors, HttpStatus.BAD_REQUEST );
            // throw an exception.  
            //throw new InvalidDataException("Invalid data", errors ); 
        } 

        token = signupImp.saveToUsers( signupmember ); 
        if ( token.getToken() == null ){ 
            throw new DataExistException("The mail has existed."); 
        } 
        // then send mail to new member. 
        return new ResponseEntity<>( token,HttpStatus.CREATED ); 

    } 

} 

