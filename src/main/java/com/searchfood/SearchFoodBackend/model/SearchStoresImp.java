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
public class SearchStoresImp extends SearchStore{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoresImp.class ); 
    private PreparedStatementSetter pss;
    private GetFoodTypesImp getFoodTypesImp; 
    private JdbcTemplate jdbc; 
    private String sqlQuery = 
            "SELECT StoreInfo.*, BusinessHours.* FROM StoreInfo INNER JOIN BusinessHours ON BusinessHours.storeId = StoreInfo.storeId ";  
    private Map<String,List<String>> storeTages; 

    @Autowired 
    public SearchStoresImp( GetFoodTypesImp g, JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
        this.getFoodTypesImp = g; 
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

    public List<Map<String,Object>> getSearchByDetailLocation( String city, String district ){ 

        log.debug("By detail location only"); 
        
        String sqlQuery = this.sqlQuery + "WHERE city = ? AND district = ?;"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,district); }; 
        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> getSearchByCity( String city ){ 

        log.debug("By City"); 
        
        String sqlQuery = this.sqlQuery + "WHERE city = ?;"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
        }; 
        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> getSearchByFoodTypeWithDetailLocation( String foodKeyWord, String city, String district ){ 

        log.debug("By Detail Location and FoodType"); 

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

    public List<Map<String,Object>> getSearchByFoodTypeWithCity( String foodKeyWord, String city ){ 

        log.debug("By City and FoodType"); 

        String sqlQuery = this.sqlQuery + 
            "WHERE city = ? AND StoreInfo.storeId IN " + 
            "( SELECT DISTINCT( StoresMenu.storeId ) FROM StoresMenu WHERE StoresMenu.foodId IN " + 
            "( SELECT ReferedTable.id FROM ReferedTable WHERE value REGEXP ? ) );"; 
        this.pss = prepareStatement -> { 
            prepareStatement.setString(1,city); 
            prepareStatement.setString(2,foodKeyWord); }; 
        
        return searchFromStoreInfo( sqlQuery, pss ); 
    } 

    public List<Map<String,Object>> searchFromStoreInfo( String sql, PreparedStatementSetter pss ){ 
        List<Map<String,Object>> resultList = this.jdbc.query( sql, pss, super::getSearchList );  
        log.debug("resultList: " + resultList ); 
        return resultList; 
    } 

} 

