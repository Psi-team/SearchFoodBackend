// This class is used for checking the token is valid or not before do some actions about database. 
package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.core.RowCallbackHandler; 

import org.springframework.stereotype.Repository; 
import org.springframework.beans.factory.annotation.Autowired; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import java.time.Instant; 
import java.time.Duration; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.LogoutImp; 

@Repository 
public class CheckTokenImp implements FindDataITF{ 

    private JdbcTemplate jdbc; 
    private String username; 
    private String token; 
    private int isExpired = 0; 
    private int validToken = 0; 
    private Instant loginTime; 
    private LogoutImp logoutImp; 
    private static final Logger log = LoggerFactory.getLogger( CheckTokenImp.class ); 

    @Autowired 
    public CheckTokenImp( JdbcTemplate jdbc, LogoutImp logout ){ 
        this.jdbc = jdbc; 
        this.logoutImp = logout; 
    } 

    public String check( String token ){ 
        this.isExpired = 0; 
        this.token = token; 
        this.validToken = isExist(); 
        if( validToken == 1 && isExpired != 1 ){
            log.info( "The username of " + token + " is " + this.username ); 
            return this.username;  // token is valid and the token is not expired. 
        }else if( validToken == 1 && isExpired == 1 ){ 
            return "TokenExpired"; // token is expired. 
        } 
        return null; // token not found.  
    } 

    @Override 
    public int isExist(){ 
        // with no try statements. 
        jdbc.query( "SELECT mail, login_time FROM Token WHERE token = ?", 
                    new RowCallbackHandler(){ 
                        @Override 
                        public void processRow( ResultSet rs ) 
                            throws SQLException{ 
                            username = rs.getString("mail"); 
                            loginTime = rs.getTimestamp("login_time").toInstant(); 
                        } 
                    }, 
                    this.token ); 
        
        if ( username == null ) return 0;  
        Instant now = Instant.now(); 
        Long duration = Duration.between( loginTime, now ).toMinutes();
        log.info("now: " + now ); 
        log.info("loginTime: " + loginTime ); 
        log.info("Duration: " + duration ); 
        if ( duration > 1 ){ // and need to delete from Token in Database.  
            isExpired = 1; 
            log.info("Token expired, logout"); 
            logoutImp.deleteFromToken( this.token ); 
        } 
        return 1; 
    } 

} 

