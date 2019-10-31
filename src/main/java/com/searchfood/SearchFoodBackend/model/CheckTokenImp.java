// This class is used for checking the token is valid or not before do some actions about database. 
package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 

public class CheckTokenImp implements FindDataITF{ 

    private JdbcTemplate jdbc; 
    private String username; 

    private static final Logger log = LoggerFactory.getLogger( CheckTokenImp.class ); 

    public CheckTokenImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public int isExist(){ 
        return 0; 
    } 

    public String check( String token ){ 
        log.debug( "The username is " + username ); 
        return username; 
    } 
} 

