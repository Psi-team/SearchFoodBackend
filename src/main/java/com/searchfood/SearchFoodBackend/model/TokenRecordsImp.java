package com.searchfood.SearchFoodBackend.model; 

// jdbc in Spring 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

// stereotype 
import org.springframework.stereotype.Repository; 

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

    private JdbcTemplate jdbc; 

    public TokenRecordsImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public int isExist( String index, String password ){ 

        try{ 

            // JdbcTemplate.query( PreparedStatementCreator psc, RowMapper<T> rowMapper, Object ... args ) 
            // RowMapper is an interface that you have to @Override  mapRow( ResultSet rs, int rowNum ) to map data into user-defined object. 
            // At the buttom of Spring in Action on page 62. 
            SignUpMember sign = 
            jdbc.queryForObject( 
                    "SELECT * FROM Users WHERE mail = ? AND passwd = ?;", 
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
                    },
                    index, password ); 

            //System.out.println( sign.toString() ); 
            System.out.println( "Member founded."); 

            return 1; 
        }catch( EmptyResultDataAccessException e ){ // Access no appropriate data in table Users. 
            System.out.println( "Member not founded."); 
            return -1; 
        } 

    } 

    @Override 
    public TokenRecords saveTokenTable( Members mem ){ 
        
        TokenRecords token = new TokenRecords(); 

        if( -1 != isExist( mem.getUsername(), mem.getPassword() ) ){ // the user who tried to login in is exactly a member. 
            // then trying to set a token; 
            token.setUsername( mem.getUsername() ); 
            token.setToken( mem.getUsername() );   
            save( token ); 
        } 

        //System.out.println( "Token not built."); 
        return token; 
    } 

    private void save( TokenRecords token ){ 
        jdbc.update( "INSERT INTO Token( mail, token, navigator_type ) VALUES( ?, ?, ? )", token.getUsername(), token.getToken(), "Chrome" );  
    } 

} 

