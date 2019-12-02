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
            "SELECT store_id AS ID, store_name, city, district, address, rating, lat_long, createAt FROM StoreInfo WHERE foodList LIKE ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 

        return searchFromStoreInfo( psc ); 
    } 

    public List<StoreInfo> getSearchByLocation( String city, String district ){ 
        String sql = 
            "SELECT store_id AS ID, store_name, city, district, address, rating, lat_long, createAt FFROM StoreInfo WHERE city = ? AND district = ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 
        return searchFromStoreInfo( psc ); 
    } 

    public List<StoreInfo> getSearchByFoodTypeWithLocation( String foodType, String city, String district ){ 
        String sql = 
            "SELECT store_id AS ID, store_name, city, district, address, rating, lat_long, createAt FROM StoreInfo WHERE city = ? AND district = ? AND foodList LIKE ?;"; 
        this.psc = 
            connection -> { 
                PreparedStatement ps = connection.prepareStatement(sql); 
                return ps; 
            }; 
        return searchFromStoreInfo( psc ); 
    } 

    private List<StoreInfo> searchFromStoreInfo( PreparedStatementCreator psc ){ 
        //List<StoreInfo> resultList = this.jdbc.query( psc, this::getList ); 
        return null; 
    } 

    private SearchStoreInfoResults getList( ResultSet rs, int rowNum ){ 
        //return new SearchStoreInfoResults( 
        //              rs.getInt("store_id"), 
        //              rs.getString("store_name"), 
        //              rs.getString("city"), 
        //              rs.getString("district"), 
        //              rs.getString("address"), 
        //              rs.getFloat("rating"), 
        //              rs.getTimestamp("createdAt"), 
        //              rs.getString("businessHours"), 
        //              rs.getString("lat_long") ); 
        return null;  
    } 
} 

