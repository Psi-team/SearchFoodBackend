package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.PreparedStatementCreator; // sql syntax has place holder, cannot use PreparedStatementCreator.  
import org.springframework.jdbc.core.PreparedStatementSetter; // sql syntax has place holder, cannot use PreparedStatementCreator.   

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import org.json.JSONObject; 
import com.google.common.base.Splitter; 

import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.GetFoodTypesImp; 

@Repository 
public class SearchStoresImp{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoresImp.class ); 
    private JdbcTemplate jdbc; 
    private PreparedStatementSetter pss;
    private GetFoodTypesImp getFoodTypesImp; 

    @Autowired 
    public SearchStoresImp( JdbcTemplate jdbc, GetFoodTypesImp g ){ 
        this.getFoodTypesImp = g; 
        this.jdbc = jdbc; 
    }

    public List<StoreInfo> getSearchByFoodType( String foodType ){ 

        log.debug("By FoodType"); 
        String whereClause = getFoodTypesImp.getFoodIdStringForQuery( foodType ); 
        log.debug("FoodIdList to used with LIKE: "+whereClause); 

        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE" + whereClause; 
        return this.jdbc.query( sql, this::getList ); 
    } 

    public List<StoreInfo> getSearchByLocation( String city, String district ){ 

        log.debug("By Location"); 

        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE city = ? AND district = ?;"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,district); 
            }; 
        return searchFromStoreInfo( sql, pss ); 
    } 

    public List<StoreInfo> getSearchByFoodTypeWithLocation( String foodType, String city, String district ){ 

        log.debug("By Location and FoodType"); 
        String likeClause = getFoodTypesImp.getFoodIdStringForQuery( foodType ); 
        log.debug("FoodIdList to used with LIKE: "+likeClause); 

        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE city = ? AND district = ? AND" + likeClause; 
        /* 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,district); 
            prepareStatement.setString(3,foodId); 
            }; 
        */ 
        return this.jdbc.query( sql, this::getList, city, district ); 
        //return searchFromStoreInfo( sql, pss ); 
    } 

    public List<StoreInfo> searchFromStoreInfo( String sql, PreparedStatementSetter pss ){ 
        log.debug("Before jdbc query."); 
        log.debug("PSS: " + pss ); 
        log.debug("SQL: " + sql ); 
        List<StoreInfo> resultList = this.jdbc.query( sql, pss, this::getList );  
        log.debug("After jdbc query."); 
        log.debug("resultList: " + resultList ); 
        return resultList; 
    } 

    private StoreInfo getList( ResultSet rs, int rowNum ) throws SQLException{ 

        log.debug("In this::getList."); 

        Map<String,String> businessHours = new HashMap(); 
        businessHours.put("星期一", rs.getString("mon")); 
        businessHours.put("星期二", rs.getString("tues")); 
        businessHours.put("星期三", rs.getString("wed")); 
        businessHours.put("星期四", rs.getString("thurs")); 
        businessHours.put("星期五", rs.getString("fri")); 
        businessHours.put("星期六", rs.getString("sat")); 
        businessHours.put("星期日", rs.getString("sun")); 

        /* 拉出來寫個function, traverse the JSONObject.getKeys()  */ 
        JSONObject latLongJSONObject = new JSONObject( rs.getString("lat_long") ); 
        log.debug("latLong: " + latLongJSONObject); 
        log.debug("latLongJSONObject.get(\"lat\"): " + latLongJSONObject.get("lat")); 
        Map<String,Integer> latLong = new HashMap<>(); 
        latLong.put("lat", (Integer) latLongJSONObject.get("lat")); 
        latLong.put("long", (Integer) latLongJSONObject.get("long")); 
        
        return new StoreInfo(
                rs.getInt("store_id"), 
                rs.getString("store_name"), 
                rs.getString("city"), 
                rs.getString("district"), 
                rs.getString("address"), 
                rs.getString("tel"), 
                rs.getString("creator"), 
                latLong, 
                null, 
                rs.getTimestamp("createdAt"), 
                businessHours, 
                rs.getFloat("rating") ); 
    } 
} 

