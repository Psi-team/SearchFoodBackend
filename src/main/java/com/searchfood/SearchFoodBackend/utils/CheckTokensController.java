package com.searchfood.SearchFoodBackend.utils; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.exceptions.TokenNotFoundException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.TokenExpiredException; 
import com.searchfood.SearchFoodBackend.model.CheckTokenImp; 
import com.searchfood.SearchFoodBackend.model.LogoutImp; 

@Service 
public class CheckTokensController{ 

    private static final Logger log = LoggerFactory.getLogger( CheckTokensController.class ); 

    @Autowired 
    private JdbcTemplate jdbc; 
    @Autowired 
    private LogoutImp logoutImp; 
    @Autowired 
    private CheckTokenImp checkTokenImp = new CheckTokenImp( jdbc, logoutImp ); 

    private String username; 

    public String check( String token ){ 

        log.info("Checking token..."); 
        username = checkTokenImp.check( token ); 
        if( username == null ){ // if the token doesn't exist in database. 
            log.warn( "The token is invalid." ); 
            throw new TokenNotFoundException("The token is invalid."); 
        }else if( username.equals("TokenExpired") ){ 
            log.warn( "The token is out of date." ); 
            throw new TokenExpiredException("Token expired."); 
        } 
        log.info("Valid token"); 

        return username; 
    } 
}; 
