package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 
import org.springframework.stereotype.Repository; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.transaction.annotation.Transactional; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.interfaces.SignUpMemberITF; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 

@Repository 
public class SignUpMemberImp implements SignUpMemberITF{ 

    private JdbcTemplate jdbc; 
    private SignUpMember signupmember; 

    private static final Logger log = LoggerFactory.getLogger( SignUpMemberImp.class ); 
    
    @Autowired 
    public SignUpMemberImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    /* 
    Override from FindDataITF interface. 
    @Override 
    public int isExist(){ 
        try{ 
            jdbc.queryForObject( 
                    "SELECT * FROM Users WHERE mail = ?", 
                    new RowMapper<SignUpMember>(){ 
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
                    signupmember.getUsername() ); 
            log.info("The member has existed."); 
            return 1; // the user who is trying to sign up has already exist in Users. 
        }catch( EmptyResultDataAccessException e ){ 
            log.info("The user is exactly a new member."); 
            return -1; // The user is exactly a new member. 
        } 
    } 
    */ 
    
    @Override 
    public TokenRecords saveToUsers( SignUpMember signupmember ){ 
        
        this.signupmember = signupmember; 
        TokenRecords token = new TokenRecords(); 
        /* 
        if ( -1 == isExist() ){ // the user is exactly a new member. 
            token.setUsername( signupmember.getUsername() );
            token.setToken();
            save( token, signupmember ); 
        } 
        
        return token; 
        */ 
        token.setUsername( signupmember.getUsername() );
        token.setToken();
        if ( save( token, signupmember ) != true ){ // if add to User and Token successfully, it should return 2.  
            return null; 
        } 
        return token; 
    } 

    //@Transactional // transaction manegement. 
    private boolean save( TokenRecords token, SignUpMember signupmember ){ 
        // transcation manegement
        try{ // May consider the transcation problem.  
            jdbc.update( "INSERT INTO Users( mail, passwd, sexual, birthyear ) VALUES( ?, ?, ?, ? )", 
                                signupmember.getUsername(), signupmember.getPasswd(), 
                                signupmember.getSexual(), signupmember.getBirthyear() );  
            jdbc.update( "INSERT INTO Token( mail, token, navigator_type ) VALUES( ?, ?, ? )", 
                                token.getUsername(), token.getToken(), "Nan" ); 
            return true; 
        } catch( DataAccessException e ){ 
            return false; 
        } 
    } 
} 

