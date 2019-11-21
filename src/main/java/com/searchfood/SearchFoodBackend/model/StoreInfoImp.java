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
import org.springframework.dao.DuplicateKeyException; 

import org.springframework.transaction.annotation.Transactional; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.SQLException; 
import java.sql.ResultSet; 
import java.sql.PreparedStatement; 

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

    @Autowired 
    public StoreInfoImp( JdbcTemplate j ){ 
        this.jdbc = j; 
    } 

    @Override 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        //storeId = 0; // Why must need this to avoid error. 
                     // 因為@Repository預設為Singleton因此一旦在runtime中storeId被改變不為初始化的0,它就不會是0,storeId會一直存在直到程式結束 
        this.username = username; 
        this.storeInfo = storeInfo; 
        log.debug( "username: " + username + "\tstoreName: " + storeInfo.getStorename() + "\n" ); 
        storeInfo.setCreator( username ); 
        // setting localdatetime to MySQL. 
        storeInfo.setCreatedAt( LocalDateTime.now(ZoneId.of("Asia/Taipei")) ); 
        if ( true != save() ) return null; 
        return storeInfo; 
    } 

    // @Transactional預設RunTimeException與非受檢例外會rollback, 受檢例外則不rollback, 透過以下參數更改為受檢例外也會rollback
    // try{}之外才會rollback?, 
    // 不能使用在private方法上,因為其不能不繼承, 
    // transactional只會作用在外部呼叫,class內的呼叫無法作用  
    // ref: https://stackoverflow.com/questions/39096860/roll-back-a-if-b-goes-wrong-spring-boot-jdbctemplate
    //@Transactional(rollbackFor=Exception.class)  
    public boolean save(){ 

        try{ 
            /* 
            jdbc.update( "INSERT INTO StoreInfo(store_name,city,district,address,tel,creator,createdAt,lat_long) VALUES(?,?,?,?,?,?,?,?,?);", 
                storeInfo.getStorename(), storeInfo.getCity(), storeInfo.getDistrict(), storeInfo.getAddress(), 
                storeInfo.getTel(), storeInfo.getCreator(), storeInfo.getCreatedAt(), storeInfo.JsonLatLongString() 
            ); 
            */ 
            // use KeyHolder to get storeId 
            String sql = 
                "INSERT INTO StoreInfo(store_name,city,district,address,tel,createdAt,creator,lat_long) VALUES(?,?,?,?,?,?,?,?,?);"; 
            KeyHolder keyHolder = new GeneratedKeyHolder(); 
            jdbc.update( connection -> { 
                            PreparedStatement ps = connection.prepareStatement( sql ); 
                            ps.setString(2,storeInfo.getStorename()); 
                            ps.setString(3,storeInfo.getCity()); 
                            ps.setString(4,storeInfo.getDistrict()); 
                            ps.setString(5,storeInfo.getAddress()); 
                            ps.setString(6,storeInfo.getTel()); 
                            ps.setTimestamp(7,storeInfo.getCreatedAt()); 
                            ps.setString(8,storeInfo.getCreator()); 
                            ps.setString(11,storeInfo.JsonLatLongString());// may cause problem. 
                            // 若使用Java Bean包裝nested Json,則必須使用JSONPObject來封裝並用toString()來存至DB. 
                            // storeInfo.getLat_long()中,必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 
                            return ps; 
                         } 
                         , keyHolder ); 
            int storeId = keyHolder.getKey().intValue(); 
            // use storeId to insert values of businessHours. 
            jdbc.update( "INSERT INTO BusinessHours( storeId, mon, tues, wed, thurs, fri, sat, sun ) VALUES(?,?,?,?,?,?,?,?);", 
                         storeId, 
                         storeInfo.getBusinessHours().get(""), storeInfo.getBusinessHours().get(""), storeInfo.getBusinessHours().get(""), 
                         storeInfo.getBusinessHours().get(""), 
                         storeInfo.getBusinessHours().get(""), storeInfo.getBusinessHours().get(""), storeInfo.getBusinessHours().get("")  
            ); 
            // use storeId to insert key values of foods 
            
            return true; 
        }catch( DuplicateKeyException e ){ 
            throw new DataExistException("Data has existed."); 
        }catch( DataAccessException e ){ 
            return false; 
        } 
    } 

} 

