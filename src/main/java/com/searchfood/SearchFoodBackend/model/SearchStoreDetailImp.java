package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.annotation.Repository; 

import org.springframework.jdbc.core.JdbcTemplate; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@Repository 
public class SearchStoreDetailImp{ 

    private static final Logger log = LoggerFactory.getLogger( SearchStoreDetailImp.class ); 

    @Autowired 
    private JdbcTemplate jdbc; 

    public StoreInfo fetchStoreDetail( int storeId ){ 
        String sqlQuery = "SELECT StoreInfo.*, Business.* FROM StoreInfo INNER JOIN BusinessHours.storeId = StoreInfo.storeId " + 
                     "WHERE StoreInfo.storeId = ?;"; 
        StoreInfo storeInfo = 
            jdbc.query(
                sqlQuery, 
                ps->{ 
                    ps.setInt(1,storeId); 
                }, 
                this::getResults ); 
    } 

    private StoreInfo getResults( ResultSet rs, int rowNum ) throws SQLException{ 

    } 

} 

