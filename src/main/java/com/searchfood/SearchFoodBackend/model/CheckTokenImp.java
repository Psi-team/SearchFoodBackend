// This class is used for checking the token is valid or not before do some actions about database. 
package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.core.RowCallbackHandler; 

import org.springframework.stereotype.Repository; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 

@Repository 
public class CheckTokenImp implements FindDataITF{ 

    private JdbcTemplate jdbc; 
    private String username; 
    private String token; 
    private static final Logger log = LoggerFactory.getLogger( CheckTokenImp.class ); 

    public CheckTokenImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public int isExist(){ 
        // with no try statements. 
        jdbc.query( "SELECT mail FROM Token WHERE token = ?", 
                    new RowCallbackHandler(){ 
                        @Override 
                        public void processRow( ResultSet rs ) 
                            throws SQLException{ 
                            username = rs.getString("mail"); // this.username = rs.getString("mail");  
                        } 
                    }, 
                    this.token ); 
        System.out.println("In isExist: " + username); 
        if ( username == null ) return 0;  
        return 1; 
    } 

    public String check( String token ){ 
        this.token = token; 
        if( isExist() == 1 ){
            log.info( "The username of " + token + " is " + this.username ); 
            return this.username; 
        } 
        return null; 
    } 

} 

