package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.UUIDGenerator; 

@Repository 
public class ResetPasswordImp{ 

    final static private Logger log = LoggerFactory.getLogger( ResetPasswordImp.class ); 
    private String username; 

    @Autowired 
    private JdbcTemplate jdbc; 

    public String checkValidEmail( String email ){ 

        username = 
            jdbc.query( 
                "SELECT username FROM Users WHERE mail = ?;", 
                ( ps ) ->{ 
                    ps.setString(1,email); 
                }, 
                ( rs ) ->{ 
                    String usernameTmp = null; 
                    while( rs.next() ){ 
                        usernameTmp = rs.getString("username"); 
                    } 
                    return usernameTmp; 
                } 
            ); 

        return username; 
    } 

    public String resetPassword( String email ){ 
        String password = (new UUIDGenerator()).getUUID().substring(0,8); 
        log.debug("New password: " + password ); 
        jdbc.update(
                "UPDATE Users SET passwd = ? WHERE mail = ?;", password, email ); 
        return password; 
    } 

} 

