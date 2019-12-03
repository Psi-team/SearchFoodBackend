package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.PreparedStatementCreator; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

@Repository 
public class SearchStoresImp{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoresImp.class ); 
    private JdbcTemplate jdbc; 
    private PreparedStatementCreator psc; 

    @Autowired 
    public SearchStoresImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    }

    public List<StoreInfo> getSearchByFoodType( String foodType ){ 
        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE foodList LIKE ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 

        return searchFromStoreInfo( psc ); 
    } 

    public List<StoreInfo> getSearchByLocation( String city, String district ){ 
        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE city = ? AND district = ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 
        return searchFromStoreInfo( psc ); 
    } 

    public List<StoreInfo> getSearchByFoodTypeWithLocation( String foodType, String city, String district ){ 
        String sql = 
            "SELECT * FROM StoreInfo INNER JOIN BusinessHours on BusinessHours.storeId = StoreInfo.store_id WHERE city = ? AND district = ? AND foodList LIKE ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 
        return searchFromStoreInfo( psc ); 
    } 

    private List<StoreInfo> searchFromStoreInfo( PreparedStatementCreator psc ){ 
        List<StoreInfo> resultList = this.jdbc.query( psc, this::getList ); 
        return resultList; 
    } 

    private StoreInfo getList( ResultSet rs, int rowNum ){ 
        Map<String,String> businessHours = new HashMap(); 
        businessHours.put("星期一", rs.getString("mon")); 
        businessHours.put("星期二", rs.getString("tues")); 
        businessHours.put("星期三", rs.getString("wed")); 
        businessHours.put("星期四", rs.getString("thurs")); 
        businessHours.put("星期五", rs.getString("fri")); 
        businessHours.put("星期六", rs.getString("sat")); 
        businessHours.put("星期日", rs.getString("sun")); 
        return new StoreInfo(
                rs.getInt("store_id"), 
                rs.getString("store_name"), 
                rs.getString("city"), 
                rs.getString("district"), 
                rs.getString("address"), 
                rs.getString("tel"), 
                rs.getString("creator"), 
                rs.getString("lat_long"), 
                rs.getString("foodList"), 
                rs.getTimestamp("createdAt"), 
                businessHours, 
                rs.getFloat("rating") ); 
    } 
} 

