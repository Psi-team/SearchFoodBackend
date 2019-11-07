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

// user-defined class 
import com.searchfood.SearchFoodBackend.model.interfaces.TokenRecordsITF; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords;  
import com.searchfood.SearchFoodBackend.model.data.Members;  
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.utils.FindDataITF; 

@Repository 
public class TokenRecordsImp implements TokenRecordsITF, FindDataITF{ 

    private static final Logger log = LoggerFactory.getLogger( TokenRecordsImp.class.getName() ); 

    private JdbcTemplate jdbc; 
    private Members mem; 

    public TokenRecordsImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public int isExist(){ 

        try{ 
            // JdbcTemplate.query( PreparedStatementCreator psc, RowMapper<T> rowMapper, Object ... args ) 
            // RowMapper is an interface that you have to @Override  mapRow( ResultSet rs, int rowNum ) to map data into user-defined object. 
            // At the buttom of Spring in Action on page 62. 
            SignUpMember sign = 
            jdbc.queryForObject( 
                    "SELECT * FROM Users WHERE mail = ? AND passwd = ?;", 
                    /* using the inner annoyous class.  
                    new RowMapper<SignUpMember> (){  
                        // Must override this function. 
                        @Override 
                        public SignUpMember mapRow( ResultSet rs, int rowNum ) throws SQLException{ 
                            return new SignUpMember( 
                                        rs.getString("mail"),
                                        rs.getString("passwd"), 
                                        rs.getInt("sexual"), 
                                        rs.getDate("birthyear").getYear() + 1900, 
                                        rs.getInt("age")
                                    );
                        } 
                    }*/
                    // using lambda expression below.  
                    ( ResultSet rs, int rowNum ) ->  
                                    new SignUpMember( 
                                        rs.getString("mail"),
                                        rs.getString("passwd"), 
                                        rs.getInt("sexual"), 
                                        rs.getDate("birthyear").getYear() + 1900, 
                                        rs.getInt("age")
                                        ),
                    mem.getUsername(), mem.getPassword() ); 

            //System.out.println( "Member founded."); 
            log.info( "Member " + mem.getUsername() + " founded." ); 

            return 1; 
        }catch( EmptyResultDataAccessException e ){ // Access no appropriate data in table Users. 
            log.info( mem.getUsername() + " not founded."); 
            return -1; 
        } 

    } 

    @Override 
    public TokenRecords saveTokenTable( Members mem ){ 
        
        this.mem = mem; 
        TokenRecords token = new TokenRecords(); 

        if( -1 != isExist() ){ // the user who tried to login in is exactly a member. 
            // then trying to set a token; 
            token.setUsername( mem.getUsername() ); 
            token.setToken();   
            save( token ); 
        } 

        //System.out.println( "Token not built."); 
        return token; 
    } 

    private int save( TokenRecords token ){ 
        return jdbc.update( "INSERT INTO Token( mail, token, navigator_type ) VALUES( ?, ?, ? )", 
                        token.getUsername(), token.getToken(), mem.getBrowser() ); 
    } 

} 

