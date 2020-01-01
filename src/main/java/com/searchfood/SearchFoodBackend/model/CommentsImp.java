package com.searchfood.SearchFoodBackend.model; 

import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.model.data.Comments; 

@Repository 
public class CommentsImp{ 

    final static private Logger log = LoggerFactory.getLogger( CommentsImp.class ); 
    private JdbcTemplate jdbc; 

    public CommentsImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    public boolean saveComments( Comments comment, String username, String picUrl ){ 
        comment.setUsername( username ); 
        String sqlInsert = 
            "INSERT INTO StoreComment( storeId, username, star, price, comments, picture ) VALUES( ?, ?, ?, ?, ?, ? );"; 
        int rows = 
            jdbc.update( 
                sqlInsert, 
                comment.getStoreId(), comment.getUsername(), comment.getStar(), 
                comment.getPrice(), comment.getComments(), picUrl ); 


        // update the average star of the certain store. 
        String sqlUpdateRating = 
            "UPDATE StorInfo SET rating = ( SELECT AVG(star) FROM StoreComment WHERE StoreComment.storeId = ? ) WHERE StorInfo.storeid = ?;"; 
        int rows2 = jdbc.update( sqlUpdateRating, comment.getStoreId(), comment.getStoreId() ); 

        log.debug("Insert into database, affect " + rows + ". "); 

        if ( (rows+rows2) == 2 ) return true; 
        return false; 
        
    } 

}

