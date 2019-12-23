package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DuplicateKeyException; 
import org.springframework.stereotype.Repository; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.transaction.annotation.Transactional; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
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

    @Override 
    public TokenRecords saveToUsers( SignUpMember signupmember ){ 
        
        this.signupmember = signupmember; 
        TokenRecords token = new TokenRecords(); 
        token.setUsername( signupmember.getUsername() );
        token.setToken();
        if ( save( token, signupmember ) != true ){ // if add to User and Token successfully, it should return 2.  
            return null; 
        } 
        return token; 
    } 

    private boolean save( TokenRecords token, SignUpMember signupmember ){ 
        try{  
            jdbc.update( "INSERT INTO Users( mail, username, passwd, sexual, birthyear ) VALUES( ?, ?, ?, ?, ? )", 
                                signupmember.getMail(), signupmember.getUsername(), signupmember.getPasswd(), 
                                signupmember.getSexual(), signupmember.getBirthyear() );  
            jdbc.update( "INSERT INTO Token( mail, token, navigator_type ) VALUES( ?, ?, ? )", 
                                signupmember.getMail(), token.getToken(), "Nan" ); 
            return true; 
        } catch( DuplicateKeyException e ){ 
            System.out.println(e.getMessage()); 
            if( e.getMessage().contains("'username'") ) 
                throw new DataExistException("username has existed."); 
            else 
                throw new DataExistException("mail has existed."); 
        } 
    } 
} 

