package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.CrossOrigin; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import javax.validation.Valid;  
import org.springframework.validation.Errors;  

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.Map; 

import com.searchfood.SearchFoodBackend.utils.exceptions.MemberNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.messages.SendResetPasswordMailService; 
import com.searchfood.SearchFoodBackend.model.ResetPasswordImp; 
import com.searchfood.SearchFoodBackend.model.data.Emails; 

@RestController
@CrossOrigin("*") 
@RequestMapping( value="resetPassword" ) 
public class ResetPasswordController{ 

    final static private Logger log = LoggerFactory.getLogger( ResetPasswordController.class ); 
    private ResetPasswordImp resetPasswordImp; 
    private SendResetPasswordMailService sendResetPasswordMailService; 

    @Autowired 
    public ResetPasswordController( ResetPasswordImp r, SendResetPasswordMailService m ){ 
        this.resetPasswordImp = r; 
        this.sendResetPasswordMailService = m; 
    } 

    @PostMapping( consumes="application/json" )  
    @ResponseStatus( HttpStatus.OK ) 
    public void resetUserPassword( @Valid @RequestBody Emails email, Errors errors ){ 

        log.debug("Email: " + email.getEmail() ); 

        String username, password; 

        if( errors.hasErrors() ){ 
            throw new InvalidDataException( errors ); 
        } 

        if ( ( username=resetPasswordImp.checkValidEmail( email.getEmail() ) ) == null ) 
            throw new MemberNotFoundException("Invalid email"); 
        else{ 
            log.debug("Starting to reset new password."); 
            // reset password. 
            password = resetPasswordImp.resetPassword( email.getEmail() ); 
            // then send mail to new member. 
            sendResetPasswordMailService.sendEmail( email, username, password ); 
            log.debug("Reseting new password is down."); 
        } 
    } 

} 

