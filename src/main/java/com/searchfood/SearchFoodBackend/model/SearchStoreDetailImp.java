package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.Map; 
import java.util.List; 
import java.util.HashMap; 
import java.util.Arrays; 
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

    public Map<String,Object> fetchStoreDetail( int storeId ){ 
        String sqlQuery = "SELECT StoreInfo.*, BusinessHours.* FROM StoreInfo INNER JOIN BusinessHours ON BusinessHours.storeId = StoreInfo.storeId " + 
                     "WHERE StoreInfo.storeId = ?;"; 
        try{ 
            resultObject = 
                jdbc.queryForObject( // replaced by jdbc PreparedStatementCreator and PrepareStatementSetter. 
                    sqlQuery, 
                    this::getSearchList, 
                    storeId );  
        }catch( RuntimeException e ){ 
            return null; 
        } 
        return resultObject; 
    } 

    @Override 
    public Map<String,Object> getSearchList( ResultSet rs, int rowNum ) throws SQLException{ 
        
        Map<String,Object> resultList = new HashMap<>(); 

        Map<String,String> businessHours = new HashMap(); 
        businessHours.put("星期一", rs.getString("mon")); 
        businessHours.put("星期二", rs.getString("tue")); 
        businessHours.put("星期三", rs.getString("wed")); 
        businessHours.put("星期四", rs.getString("thu")); 
        businessHours.put("星期五", rs.getString("fri")); 
        businessHours.put("星期六", rs.getString("sat")); 
        businessHours.put("星期日", rs.getString("sun")); 

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

} 

