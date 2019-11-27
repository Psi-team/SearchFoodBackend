package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

import org.springframework.stereotype.Repository; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 

@Repository 
public class LogoutImp{ 
    
    private JdbcTemplate jdbc; 
    private String token; 
    
    private static final Logger log = LoggerFactory.getLogger( LogoutImp.class ); 
    
    @Autowired 
    public LogoutImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 


    public int deleteFromToken( String o ){ 
        this.token = o; 

        if ( delete( token ) != 1 ){ 
            return -1; 
        } 
        return 1; 
    } 

    private int delete( String token ){ 
        try{ 
            return jdbc.update( "DELETE FROM Token WHERE Token = ?", token ); 
        }catch( DataAccessException e ){ 
            return -1; 
        } 
    } 

} 

