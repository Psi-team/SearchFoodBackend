package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.FindDataITF; 
import com.searchfood.SearchFoodBackend.model.interfaces.StoreInfoITF; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 

public class StoreInfoImp implements StoreInfoITF, FindDataITF{ 

    private final static Logger log = LoggerFactory.getLogger( StoreInfoImp.class ); 
    private JdbcTemplate jdbc; 

    @Autowired 
    public StoreInfoImp( JdbcTemplate j ){ 
        this.jdbc = j; 
    } 

    @Override 
    public boolean createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        return true; 
    } 

    @Override 
    public int isExist(){ 
        return 0; 
    } 

    private int save(){ 
        return 0; 
    } 

} 

