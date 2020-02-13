package com.searchfood.SearchFoodBackend.model; 

// jdbc in Spring 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

// stereotype 
import org.springframework.stereotype.Repository; 

// logging import org.slf4j.Logger; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

// Annotation
import org.springframework.beans.factory.annotation.Autowired; 

// java.sql 
import java.sql.ResultSet; 
import java.sql.SQLException; 

import java.util.Optional; 

// user-defined class 
import com.searchfood.SearchFoodBackend.model.interfaces.TokenRecordsITF; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords;  
import com.searchfood.SearchFoodBackend.model.data.Members;  
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.utils.FindDataITF; 

@Repository 
public class TokenRecordsImp{ 

    private static final Logger log = LoggerFactory.getLogger( TokenRecordsImp.class.getName() ); 

    private JdbcTemplate jdbc; 
    private Members mem; 
    private SignUpMember results; 

    public TokenRecordsImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    public int isExist(){ 

        try{ 
            // JdbcTemplate.query( PreparedStatementCreator psc, RowMapper<T> rowMapper, Object ... args ) 
            // RowMapper is an interface that you have to @Override  mapRow( ResultSet rs, int rowNum ) to map data into user-defined object. 
            // At the buttom of Spring in Action on page 62. 
            this.results = jdbc.queryForObject( 
                    "SELECT * FROM Users WHERE mail = ? AND passwd = ?;", 
                    // using lambda expression below.  
                    ( ResultSet rs, int rowNum ) ->  
                                    new SignUpMember( 
                                        rs.getString("mail"),
                                        rs.getString("username"),  
                                        rs.getString("passwd"), 
                                        rs.getInt("sexual"), 
                                        rs.getDate("birthyear").getYear() + 1900, 
                                        rs.getInt("age") 
                                        ),
                    mem.getMail(), mem.getPasswd() ); 

            //System.out.println( "Member founded."); 
            log.info( "Member " + mem.getMail() + " founded." ); 

            return 1; 
        }catch( EmptyResultDataAccessException e ){ // Access no appropriate data in table Users. 
            log.info( mem.getMail() + " not founded."); 
            return -1; 
        } 

    } 

    public Optional<TokenRecords> saveTokenTable( Members mem ){ 
        
        this.mem = mem; 
        TokenRecords token = new TokenRecords(); 

        if( -1 != isExist() ){ // the user who tried to login in is exactly a member. 
            // then trying to set a token; 
            token.setUsername( this.results.getUsername() ); 
            token.setToken();   
            save( token ); 
            log.info( "Username before Optional: " + token.getUsername() ); 
            Optional<TokenRecords> opt_token = Optional.of( token ); 
            log.info( "Username after Optional: " + opt_token.get().getUsername() ); 
            return opt_token; 
        } 

        //System.out.println( "Token not built."); 
        return Optional.ofNullable( null ); 
    } 

    private int save( TokenRecords token ){ 
        return jdbc.update( "INSERT INTO Token( mail, username, token, navigator_type ) VALUES( ?, ?, ?, ? )", 
                        mem.getMail(), token.getUsername(), token.getToken(), mem.getBrowser() ); 
    } 

} 

