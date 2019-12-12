package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.BatchPreparedStatementSetter; 
import org.springframework.jdbc.core.RowCallbackHandler; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.support.KeyHolder; 
import org.springframework.jdbc.support.GeneratedKeyHolder; 

import org.springframework.dao.DuplicateKeyException; 
import org.springframework.dao.DataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.SQLException; 
import java.sql.ResultSet; 
import java.sql.PreparedStatement; 
import java.sql.Statement; 

import java.time.LocalDateTime; 
import java.time.ZoneId; 

import java.util.List; 
import java.util.ArrayList; 
import java.util.function.BiConsumer; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.interfaces.StoreInfoITF; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.GetFoodTypesImp; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.TokenNotFoundException; 

@Repository 
public class StoreInfoImp implements StoreInfoITF{ 

    private final static Logger log = LoggerFactory.getLogger( StoreInfoImp.class ); 
    private JdbcTemplate jdbc; 
    private String username; 
    private StoreInfo storeInfo; 
    private GetFoodTypesImp getFoodTypesImp; 

    @Autowired 
    public StoreInfoImp( JdbcTemplate j, GetFoodTypesImp g ){ 
        this.jdbc = j; 
        this.getFoodTypesImp = g; 
    } 

    @Override 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        //storeId = 0; // Why must need this to avoid error. 
                     // 因為@Repository預設為Singleton因此一旦在runtime中storeId被改變不為初始化的0,它就不會是0,storeId會一直存在直到程式結束 
        this.username = username; 
        this.storeInfo = storeInfo; 
        storeInfo.setCreator( username ); 
        // setting localdatetime to MySQL. 
        storeInfo.setCreatedAt( LocalDateTime.now(ZoneId.of("Asia/Taipei")) ); 
        storeInfo.setTags( new ArrayList( storeInfo.getType().keySet() ) ); 
        log.debug( "username: " + username + "\tstoreName: " + storeInfo.getStorename() + ". At " + storeInfo.getCreatedAt() ); 
        log.debug("tags: " + storeInfo.getTags().toString() ); 

        if ( true != save(storeInfo) ) return null; 
        return storeInfo; 
    } 

    public boolean save(StoreInfo storeInfo){ 

        //try{ 
            // use KeyHolder to get storeId 
            String sql = 
                "INSERT INTO StoreInfo(storename,city,district,address,tel,createdAt,creator,lat_long, tags) VALUES(?,?,?,?,?,?,?,?,?);"; 
            KeyHolder keyHolder = new GeneratedKeyHolder(); 
            jdbc.update( connection -> { 
                            PreparedStatement ps = connection.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ); // setting keyHolder by 2nd arg. 
                            // the first arguement of setXXX() is the order of the arg of SQL statement. 
                            ps.setString(1,storeInfo.getStorename()); 
                            ps.setString(2,storeInfo.getCity()); 
                            ps.setString(3,storeInfo.getDistrict()); 
                            ps.setString(4,storeInfo.getAddress()); 
                            ps.setString(5,storeInfo.getTel()); 
                            ps.setTimestamp(6,storeInfo.getCreatedAt()); 
                            ps.setString(7,storeInfo.getCreator()); 
                            ps.setString(8,storeInfo.JsonLatLongString());// may cause problem. 
                            // 若使用Java Bean包裝nested Json,則必須使用JSONPObject來封裝並用toString()來存至DB. 
                            // storeInfo.getLatlong()中,必須將JSONObject用toString()輸出才能存至MySQL的JSON欄位 return ps; 
                            ps.setString(9,storeInfo.getTags().toString().substring(1,storeInfo.getTags().toString().length()-1) ); 
                            return ps; 
                         }, 
                         keyHolder ); 
            // use storeId to insert values of businessHours. 
            int storeId = keyHolder.getKey().intValue(); 
            log.debug("Afer updating StoreInfo, the storeId is " + storeId); 
            log.debug("BusinessHours are " + storeInfo.getBusinessHours()); 
            jdbc.update( "INSERT INTO BusinessHours( storeId, mon, tue, wed, thu, fri, sat, sun ) VALUES(?,?,?,?,?,?,?,?);", 
                         storeId, 
                         storeInfo.getBusinessHours().get("星期一"), 
                         storeInfo.getBusinessHours().get("星期二"), 
                         storeInfo.getBusinessHours().get("星期三"), 
                         storeInfo.getBusinessHours().get("星期四"), 
                         storeInfo.getBusinessHours().get("星期五"), 
                         storeInfo.getBusinessHours().get("星期六"), 
                         storeInfo.getBusinessHours().get("星期日")  
            ); 
            // use storeId to insert key values of foods 
            List<String> detailsList = new ArrayList(); 
            storeInfo.getType()
                .forEach( 
                    new BiConsumer<String,List>(){ 
                        @Override 
                        public void accept( String types, List details ){ 
                            detailsList.addAll( storeInfo.getType().get(types) ); 
                        } 
                    }
                ); 
            log.debug("The details of food types: " + detailsList);  
            List<Integer> foodIdList =  this.getFoodTypesImp.getStoreMenuList( detailsList ); 
            log.debug("The foodId of details." + foodIdList ); 
            /* saving the foodId and storeId to the table StoresMenu. */ 
            jdbc.batchUpdate( 
                    "INSERT INTO StoresMenu( storeId, foodId ) VALUES( ?, ? );", 
                    new BatchPreparedStatementSetter(){ 
                        @Override 
                        public void setValues( PreparedStatement ps, int i ) throws SQLException{ 
                            ps.setInt(1, storeId); 
                            ps.setInt(2, foodIdList.get(i)); 
                        } 
                        @Override 
                        public int getBatchSize(){ 
                            return foodIdList.size(); 
                        } 
                    } 
            );
            
            return true; 
        //}catch( DuplicateKeyException e ){ 
        //    return false; 
            //throw new DataExistException("Data has existed."); 
        //}catch( DataAccessException e ){ 
        //    return false;  
        //}catch( SQLException e ){ 
        //    return false; 
        //} 
    } 


} 

