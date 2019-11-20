package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowCallbackHandler; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.support.KeyHolder; 
import org.springframework.jdbc.support.GeneratedKeyHolder; 

import org.springframework.dao.EmptyResultDataAccessException; 
import org.springframework.dao.DataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.SQLException; 
import java.sql.ResultSet; 

import java.time.LocalDateTime; 
import java.time.ZoneId; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.interfaces.StoreInfoITF; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 

@Repository 
public class StoreInfoImp implements StoreInfoITF{ 

    private final static Logger log = LoggerFactory.getLogger( StoreInfoImp.class ); 
    private JdbcTemplate jdbc; 
    private String username; 
    private StoreInfo storeInfo; 
    private int storeId = 0; 

    @Autowired 
    public StoreInfoImp( JdbcTemplate j ){ 
        this.jdbc = j; 
    } 

    /* 
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
                            log.info("In isExist, jdbc.query(): storeId" + storeId); 
                    } 
                }, 
                this.storeInfo.getStorename(), this.storeInfo.getCity(), this.storeInfo.getDistrict(), this.storeInfo.getAddress() 
        );
        if ( 0 == storeId ){ 
            log.info( "The store hasn't existed in StoreId." ); 
            return -1; 
        }else{ 
            log.info( "The store has existed in StoreInfo." ); 
            return 1; 
        } 
        
    } 
    */ 

    @Override 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        storeId = 0; // Why must need this to avoid error. 
                     // 因為@Repository預設為Singleton因此一旦在runtime中storeId被改變不為初始化的0,它就不會是0,storeId會一直存在直到程式結束 
        this.username = username; 
        this.storeInfo = storeInfo; 
        log.debug( "username: " + username + "\tstoreName: " + storeInfo.getStorename() + "\n" ); 
        //if ( 1 == isExist() ) return null; // the store has already existed in the databases;  
        storeInfo.setCreator( username ); 
        // setting localdatetime to MySQL. 
        storeInfo.setCreatedAt( LocalDateTime.now(ZoneId.of("Asia/Taipei")) ); 
        if ( 1 != save() ) return null; 
        return storeInfo; 
    } 

    private int save(){ 

        try{ 
            return 
                // use KeyHolder to get storeId 
            jdbc.update( "INSERT INTO StoreInfo(store_name,city,district,address,tel,creator,createdAt,lat_long) VALUES(?,?,?,?,?,?,?,?,?);", 
                storeInfo.getStorename(), storeInfo.getCity(), storeInfo.getDistrict(), storeInfo.getAddress(), 
                storeInfo.getTel(), storeInfo.getCreator(), storeInfo.getCreatedAt(), storeInfo.JsonLatLongString() 
                // 若使用Java Bean包裝nested Json,則必須使用JSONPObject來封裝並用toString()來存至DB. 
                // storeInfo.getLat_long()中,必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
                // storeInfo.getTypes()中,必須將JSONArray用toString()輸出才能存至MySQL的JSON欄位 
            ); 
            // use storeId to insert values of businessHours. 
            // use storeId to insert key values of foods 
        }catch( DataAccessException e ){ 
            return 0; 
        } 
    } 

} 

