package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowCallbackHandler; 
import org.springframework.jdbc.core.RowMapper; 

import org.springframework.dao.EmptyResultDataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.SQLException; 
import java.sql.ResultSet; 

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
    private int storeId; 

    @Autowired 
    public StoreInfoImp( JdbcTemplate j ){ 
        this.jdbc = j; 
    } 

    @Override 
    public int isExist(){ 
        jdbc.query( 
                "SELECT store_id FROM StoreInfo WHERE (store_name=?) AND (city=?) AND (district=?) AND (address=?);", 
                new RowCallbackHandler(){ 
                    @Override 
                    public void processRow( ResultSet rs ) throws SQLException{ 
                        storeId = rs.getInt("store_id"); 
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
    public boolean createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        this.username = username; 
        this.storeInfo = storeInfo; 
        log.debug( "username: " + username + "\n" + 
                   "\t\t\tstoreName: " + storeInfo.getStorename() + "\n" ); 
        if ( 1 == isExist() ) return false; // the store has already existed in the databases;  
        save(); 
        return true; 
    } 

    private int save(){ 
        jdbc.update( "INSERT INTO StoreInfo(store_name,city,district,address,tel,creator,lat_long,types) VALUES(?,?,?,?,?,?,?,?);", 
            storeInfo.getStorename(), storeInfo.getCity(), storeInfo.getDistrict(), storeInfo.getAddress(), 
            storeInfo.getTel(), username, storeInfo.getLat_long(), storeInfo.getTypes() 
        ); 
        return 1; 
    } 

} 

