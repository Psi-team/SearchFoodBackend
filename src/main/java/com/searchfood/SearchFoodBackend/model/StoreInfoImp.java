package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowCallbackHandler; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.support.KeyHolder; 
import org.springframework.jdbc.support.GeneratedKeyHolder; 

import org.springframework.dao.EmptyResultDataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.SQLException; 
import java.sql.ResultSet; 
import java.util.Date; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.interfaces.StoreInfoITF; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 

@Repository 
public class StoreInfoImp implements StoreInfoITF, FindDataITF{ 

    private final static Logger log = LoggerFactory.getLogger( StoreInfoImp.class ); 
    private JdbcTemplate jdbc; 
    private String username; 
    private StoreInfo storeInfo; 
    private int storeId = 0; 

    @Autowired 
    public StoreInfoImp( JdbcTemplate j ){ 
        this.jdbc = j; 
    } 

    @Override 
    public int isExist(){ 
        // Why try catch statement doesn't work. 
        jdbc.query( 
                "SELECT store_id FROM StoreInfo WHERE (store_name=?) AND (city=?) AND (district=?) AND (address=?);", 
                new RowCallbackHandler(){ 
                    @Override 
                    public void processRow( ResultSet rs ) 
                        throws SQLException{ 
                            storeId = rs.getInt("store_id"); 
                            System.out.println("In isExist, jdbc.query(): " + storeId); 
                    } 
                }, 
                this.storeInfo.getStorename(), this.storeInfo.getCity(), this.storeInfo.getDistrict(), this.storeInfo.getAddress() 
        );
        if ( 0 == storeId ){ 
            log.info( "The store hasn't existed in StoreId. store_id: " + storeId ); 
            return -1; 
        }else{ 
            log.info( "The store has existed in StoreInfo. store_id: " + storeId ); 
            return 1; 
        } 
        
    } 

    @Override 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        storeId = 0; // Why must need this to avoid error.  
        this.username = username; 
        this.storeInfo = storeInfo; 
        log.debug( "username: " + username + "\n" + 
                   "\t\t\tstoreName: " + storeInfo.getStorename() + "\n" ); 
        if ( 1 == isExist() ) return null; // the store has already existed in the databases;  
        storeInfo.setCreator( username ); 
        storeInfo.setCreatedDate( new Date() ); 
        SaveAndQuery(); 
        return storeInfo; 
    } 

    private int SaveAndQuery(){ 

        //KeyHolder keyHolder = new GeneratedKeyHolder(); 
        
        // jdbc.update( PreparedStatementCreator, KeyHolder ); 
        jdbc.update( "INSERT INTO StoreInfo(store_name,city,district,address,tel,creator,create_date,lat_long,types) VALUES(?,?,?,?,?,?,?,?,?);", 
            storeInfo.getStorename(), storeInfo.getCity(), storeInfo.getDistrict(), storeInfo.getAddress(), 
            storeInfo.getTel(), storeInfo.getCreator(), storeInfo.getCreatedDate(), storeInfo.getLat_long(), storeInfo.getTypes() 
        ); 

        //jdbc.query( 
        //    "SELECT created_date FROM StoreInfo WHERE store_id=?;", 
        //); 
        return 1; 
    } 

} 

