package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.PreparedStatementCreator; // sqlQuery syntax has place holder, cannot use PreparedStatementCreator.  
import org.springframework.jdbc.core.PreparedStatementSetter; // sqlQuery syntax has place holder, cannot use PreparedStatementCreator.   

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import org.json.JSONObject; 
import com.google.common.base.Splitter; 

import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Arrays; 
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
    private String sqlQuery = 
            "SELECT StoreInfo.*, BusinessHours.* FROM StoreInfo INNER JOIN BusinessHours ON BusinessHours.storeId = StoreInfo.storeId ";  
    private Map<String,List<String>> storeTages; 

    @Autowired 
    public SearchStoresImp( JdbcTemplate jdbc, GetFoodTypesImp g ){ 
        this.getFoodTypesImp = g; 
        this.jdbc = jdbc; 
    }

    public List<Map<String,Object>> getSearchByFoodKeyWord( String foodKeyWord ){ 

        log.debug("By Food Key Word"); 

        String sqlQuery = this.sqlQuery + 
            "WHERE StoreInfo.storeId IN " + 
            "( SELECT DISTINCT( StoresMenu.storeId ) FROM StoresMenu WHERE StoresMenu.foodId IN " + 
            "( SELECT ReferedTable.id FROM ReferedTable WHERE value REGEXP ? ) );"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,foodKeyWord); }; 

        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> getSearchByLocation( String city, String district ){ 

        log.debug("By Location"); 
        
        String sqlQuery = this.sqlQuery + "WHERE city = ? AND district = ?;"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,district); }; 
        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> getSearchByFoodTypeWithLocation( String foodKeyWord, String city, String district ){ 

        log.debug("By Location and FoodType"); 

        String sqlQuery = this.sqlQuery + 
            "WHERE city = ? AND district = ? AND StoreInfo.storeId IN " + 
            "( SELECT DISTINCT( StoresMenu.storeId ) FROM StoresMenu WHERE StoresMenu.foodId IN " + 
            "( SELECT ReferedTable.id FROM ReferedTable WHERE value REGEXP ? ) );"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,district); 
            prepareStatement.setString(3,foodKeyWord); }; 
        
        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> searchFromStoreInfo( String sql, PreparedStatementSetter pss ){ 
        List<Map<String,Object>> resultList = this.jdbc.query( sql, pss, this::getList );  
        log.debug("resultList: " + resultList ); 
        return resultList; 
    } 

    private Map<String,Object> getList( ResultSet rs, int rowNum ) throws SQLException{ 

        Map<String,Object> resultList = new HashMap<>(); 

        Map<String,String> businessHours = new HashMap(); 
        businessHours.put("星期一", rs.getString("mon")); 
        businessHours.put("星期二", rs.getString("tue")); 
        businessHours.put("星期三", rs.getString("wed")); 
        businessHours.put("星期四", rs.getString("thu")); 
        businessHours.put("星期五", rs.getString("fri")); 
        businessHours.put("星期六", rs.getString("sat")); 
        businessHours.put("星期日", rs.getString("sun")); 

        /* 拉出來寫個function, traverse the JSONObject.getKeys()  */ 
        JSONObject latLongJSONObject = new JSONObject( rs.getString("lat_long") ); 
        Map<String,Integer> latLong = new HashMap<>(); 
        latLong.put("lat", (Integer) latLongJSONObject.get("lat")); 
        latLong.put("long", (Integer) latLongJSONObject.get("long")); 

        resultList.put("storeId",rs.getInt("storeId")); 
        resultList.put("storename",rs.getString("storename")); 
        resultList.put("city",rs.getString("city")); 
        resultList.put("district",rs.getString("district")); 
        resultList.put("address",rs.getString("address")); 
        resultList.put("tel",rs.getString("tel")); 
        resultList.put("latLong",latLong);  
        resultList.put("businessHours",businessHours); 
        resultList.put("star",rs.getFloat("rating"));
        resultList.put("tags", Arrays.asList(rs.getString("tags").split(","))); 
        resultList.put("slogan",rs.getString("slogan"));
        //resultList.put("images",rs.getString("images"));
        //resultList.put("logo",rs.getString("logo"));
        resultList.put("createdDate",rs.getTimestamp("createdAt").toLocalDateTime().toLocalDate());

        return resultList; 
    } 
} 

