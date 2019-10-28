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
import com.searchfood.SearchFoodBackend.model.data.tmpData; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 

@Repository 
public class Logout implements FindDataITF{ 
    
    private JdbcTemplate jdbc; 
    private TokenRecords token; 
    
    private static final Logger log = LoggerFactory.getLogger( Logout.class ); 
    
    @Autowired 
    public Logout( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    @Override 
    public int isExist(){ 
        try{ 
            jdbc.queryForObject( 
                    "SELECT token FROM Token WHERE token = ?", 
                    new RowMapper<tmpData> (){  
                        // Must override this function. 
                        @Override 
                        public tmpData mapRow( ResultSet rs, int rowNum ) throws SQLException{ 
                            return new tmpData( 
                                        rs.getString("token") 
                                    ); 
                        } 
                    },
                    token.getToken() ); 

            log.info( "Token founded."); 
            return 1; 
        }catch( EmptyResultDataAccessException e ){ // Access no appropriate data in table Users. 
            log.info("token not founded."); 
            return -1; 
        } 

    } 

    public int deleteFromToken( TokenRecords o ){ 
        this.token = o; 

        if ( 1 == isExist() ){ // if token exists in Token, then delete it. 
            delete( token ); 
            return 1; // delete token from Token sucessfully. 
        } 
        return -1; // fail to delete token from Token. 
    } 

    private int delete( TokenRecords token ){ 
        if ( 0 != jdbc.update( "DELETE FROM Token WHERE Token = ?", token.getToken() ) ){ 
            return 1;   
        } 
        return -1; 
    } 

} 

