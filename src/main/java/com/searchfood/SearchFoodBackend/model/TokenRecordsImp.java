package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.PreparedStatementCreator; 
import org.springframework.jdbc.core.PreparedStatementCreatorFactory; 
import org.springframework.jdbc.support.KeyHolder; 
import org.springframework.jdbc.support.GeneratedKeyHolder; 

import org.springframework.stereotype.Repository; 

import com.searchfood.SearchFoodBackend.model.interfaces.TokenRecordsITF; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords;  

@Repository 
public class TokenRecordsImp implements TokenRecordsITF{ 

    private JdbcTemplate jdbc; 

    public TokenRecordsImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public boolean isExist( String username ){ 
        return true; 
    } 

    @Override 
    public TokenRecords saveTokenTable( TokenRecords token ){ 
        return token; 
    } 

} 

