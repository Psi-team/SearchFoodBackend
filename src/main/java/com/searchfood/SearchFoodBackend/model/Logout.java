package com.searchfood.SearchFoodBackend.model; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

import org.springframework.stereotype.Repository; 

import org.springframework.beans.factory.annotation.Autowired; 

import java.sql.ResultSet; 
import java.sql.SQLException; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.data.tmpData; 
import com.searchfood.SearchFoodBackend.model.data.OnlineMembers; 

@Repository 
public class Logout implements FindDataITF{ 
    
    private JdbcTemplate jdbc; 
    private OnlineMembers onlineMembers; 
    
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
                    onlineMembers.getToken() ); 

            System.out.println( "Token founded."); 
            return 1; 
        }catch( EmptyResultDataAccessException e ){ // Access no appropriate data in table Users. 
            System.out.println( "token not founded."); 
            return -1; 
        } 

    } 

    public int deleteFromToken( OnlineMembers o ){ 
        this.onlineMembers = o; 

        if ( 1 == isExist() ){ // if token exists in Token, then delete it. 
            delete( onlineMembers ); 
            return 1; // delete token from Token sucessfully. 
        } 
        return -1; // fail to delete token from Token. 
    } 

    private int delete( OnlineMembers onlineMembers ){ 
        if ( 0 != jdbc.update( "DELETE FROM Token WHERE Token = ?", onlineMembers.getToken() ) ){ 
            return 1;   
        } 
        return -1; 
    } 

} 

