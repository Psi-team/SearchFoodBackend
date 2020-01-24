package com.searchfood.SearchFoodBackend.webapi; 

// Annotations 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
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
//import com.searchfood.SearchFoodBackend.model.SignUpMemberTransactionImp; 
import com.searchfood.SearchFoodBackend.model.TransactionManagement; 
// logging import org.slf4j.Logger; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.messages.interfaces.SignUpMailService; 

@RestController 
@CrossOrigin("*") 
@RequestMapping( value="signup", produces="application/json" ) 
public class SignUpController{ 

    private TransactionManagement transactionManagement;//private SignUpMemberTransactionImp signupTransactionImp;  
    private TokenRecords token; 
    private SignUpMailService signupMailService; 

    private static final Logger log = LoggerFactory.getLogger( SignUpController.class ); 

    @Autowired 
    public SignUpController( TransactionManagement transactionManagement, SignUpMailService signupMailService ){ 
        this.transactionManagement = transactionManagement; 
        this.signupMailService = signupMailService; 
    } 
    
    @PostMapping( consumes="application/json" ) 
    public ResponseEntity<?> signUp( @Valid @RequestBody SignUpMember signupmember, 
                                Errors errors ){ 

        log.debug("mail: " + signupmember.getMail() + "username: " + signupmember.getUsername() + 
                            " password: " + signupmember.getPasswd() + 
                            " birthyear: " + signupmember.getBirthyear() + " sex: " + signupmember.getSexual() ); 
        
        if( errors.hasErrors() ){ 
            throw new InvalidDataException( errors ); 
        } 

        this.token = transactionManagement.saveToUsers( signupmember ); 
        if ( token == null ){ 
            throw new DataExistException("The mail has existed."); 
        } 

        // then send mail to new member. 
        signupMailService.sendEmail( signupmember ); 

        log.info( token.getUsername() + " sign up successfully." );  
        
        return new ResponseEntity<>( token,HttpStatus.CREATED ); 

    } 

} 

