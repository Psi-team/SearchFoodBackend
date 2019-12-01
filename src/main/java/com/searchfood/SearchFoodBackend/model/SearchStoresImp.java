package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Repository; 
import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.sql.PreparedStatement; 
import java.util.List; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

@Repository 
public class SearchStoresImp{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStoresImp.class ); 
    private JdbcTemplate jdbc; 
    private PreparedStatement ps; 

    @Autowired 
    public SearchStoresImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    }

    public List<StoreInfo> getSearchByFoodType( String foodType ){ 
        return null; 
    } 

    public List<StoreInfo> getSearchByLocation( String city, String district ){ 
        return null; 
    } 

    public List<StoreInfo> getSearchByFoodTypeWithLocation( String foodType, String city, String district ){ 
        return null; 
    } 

    private List<StoreInfo> searchFromStoreInfo( PreparedStatement psc ){ 
        return null; 
    } 

} 

