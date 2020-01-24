package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 

import org.springframework.dao.DuplicateKeyException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.Map; 
import java.util.List; 
import java.util.HashMap; 
import java.util.Arrays; 
import java.util.ArrayList; 
import java.sql.ResultSet; 
import java.sql.SQLException;

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.SearchStore; 

@Repository 
public class SearchStoreDetailImp extends SearchStore{ 

    private static final Logger log = LoggerFactory.getLogger( SearchStoreDetailImp.class ); 
    private Map<String,Object> resultObject = new HashMap<>(); 

    @Autowired 
    private JdbcTemplate jdbc; 

    public Map<String,Object> fetchStoreDetail( int storeId, String username ){ 

        try{

            String sqlQuery = 
                "SELECT StoreInfo.*, BusinessHours.* FROM StoreInfo INNER JOIN BusinessHours ON BusinessHours.storeId = StoreInfo.storeId " + 
                     "WHERE StoreInfo.storeId = ?;"; 
            // search the certain storeId. 
            resultObject = 
                jdbc.queryForObject( // replaced by jdbc PreparedStatementCreator and PrepareStatementSetter. 
                    sqlQuery, 
                    this::getSearchList, 
                    storeId );  
            resultObject.put("comments", getCommentList(storeId)); 

            // Need to check that username whether has click the specific storeId first. 
            // add the restrict click to certain store detail.
            // if exists in the ClickRecords, then it will be catched 
            String sqlUpdateClickRecords = 
                "INSERT INTO ClickRecord( username, storeId ) VALUES( ?, ? );"; 
            jdbc.update( sqlUpdateClickRecords, username, storeId ); 
            // if not. 
            // update the click_cum after query the certain store detail. 
            String sqlUpdateClick = 
                "UPDATE StoreInfo SET click_cum = click_cum + 1 WHERE storeid = ?"; 
            jdbc.update( sqlUpdateClick, storeId ); 
            log.debug("The click_cum has updated."); 

        }catch( DuplicateKeyException e ){ 

            log.debug("The username has already clicked this store. No update for click_cum"); 

        }catch( RuntimeException e ){ 

            e.printStackTrace(); 
            return null; 

        } 
        return resultObject; 

    } 

    @Override 
    public Map<String,Object> getSearchList( ResultSet rs, int rowNum ) throws SQLException{ 
        
        Map<String,String> businessHours = new HashMap(); 
        businessHours.put("星期一", rs.getString("mon")); 
        businessHours.put("星期二", rs.getString("tue")); 
        businessHours.put("星期三", rs.getString("wed")); 
        businessHours.put("星期四", rs.getString("thu")); 
        businessHours.put("星期五", rs.getString("fri")); 
        businessHours.put("星期六", rs.getString("sat")); 
        businessHours.put("星期日", rs.getString("sun")); 

        Map<String,Object> resultList = new HashMap<>(); 

        resultList.put("storename",rs.getString("storename")); 
        resultList.put("tags", Arrays.asList(rs.getString("tags").split(","))); 
        resultList.put("star",rs.getFloat("rating"));
        resultList.put("slogan",rs.getString("slogan"));
        resultList.put("businessHours",businessHours); 
        resultList.put("tel",rs.getString("tel")); 
        resultList.put("address",rs.getString("city")+rs.getString("district")+rs.getString("address")); 
        resultList.put("pictures",Arrays.asList( rs.getString("logo"), rs.getString("images") )); 
        //resultList.put("latLong",latLong);  
        //resultList.put("createdDate",rs.getTimestamp("createdAt").toLocalDateTime().toLocalDate());
        //resultList.put("creator",rs.getString("creator"));

        return resultList; 
    } 

    private List<Map<String,Object>> getCommentList( int storeId ){ 
        
        List<Map<String,Object>> result = new ArrayList<>(); 
        String sqlQuery = "SELECT username, commentAt, comments, picture FROM StoreComment WHERE storeId = ?;"; 
        result = 
            jdbc.query( 
                    sqlQuery, 
                    ( ps ) -> { 
                        ps.setInt(1,storeId); 
                    }, 
                    this::getResults ); 
        return result; 

    } 

    private Map<String,Object> getResults( 
                ResultSet rs, int rowNum ) throws SQLException{ 

        Map<String,Object> map = new HashMap<>(); 
        map.put("createUser", rs.getString("username")); 
        map.put("createDate", rs.getTimestamp("commentAt").toLocalDateTime().toLocalDate()); 
        map.put("contents", rs.getString("comments")); 
        map.put("pictures", rs.getString("picture")); 

        return map; 
    } 

} 

