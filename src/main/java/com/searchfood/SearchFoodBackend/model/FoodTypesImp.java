package com.searchfood.SearchFoodBackend.model; 

import org.springframework.stereotype.Repository; 
import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import org.json.JSONArray; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.function.Consumer; 

import com.searchfood.SearchFoodBackend.model.data.FoodTypes; 

@Repository 
public class FoodTypesImp{ 

    private static final Logger log = LoggerFactory.getLogger( FoodTypesImp.class ); 
    private FoodTypes foodTypes;
    private JdbcTemplate jdbc; 

    @Autowired 
    public FoodTypesImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    public JSONArray getFoodTypesJson(){ 
        JSONArray results = getQueryResults(); 
        return results; 
    } 

    private JSONArray getQueryResults(){ 
        List<FoodTypes> foodTypesList = 
                    jdbc.query( 
                            "SELECT * FROM FoodTypes;", 
                            this::getFoodTypes 
                    ); 
        return toJSONArray(foodTypesList); 
    } 

    private JSONArray toJSONArray( List target ){ 
        List<String> Rice = new ArrayList(); 
        List<String> Noodles = new ArrayList(); 
        List<String> FastFood = new ArrayList(); 

        target.forEach( 
                new Consumer<FoodTypes>(){ 
                    @Override 
                    public void accept( FoodTypes target_ ){ 
                        switch( target_.getTypes() ){ 
                            case "飯": 
                                Rice.add( target_.getDetails() ); 
                                break; 
                            case "麵食": 
                                Noodles.add( target_.getDetails() ); 
                                break; 
                            case "速食": 
                                FastFood.add( target_.getDetails() ); 
                                break; 
                        } 
                    } 
                } 
        ); 

        Map<String,List<String>> resultMap = new HashMap(); 
        resultMap.put("飯",Rice); 
        resultMap.put("麵食",Noodles); 
        resultMap.put("速食",FastFood); 
        return new JSONArray( resultMap ); 
    } 

    private FoodTypes getFoodTypes( ResultSet rs, int rowNum ) throws SQLException{ 
        return new FoodTypes( 
                rs.getInt("foodId"), 
                rs.getString("details"), 
                rs.getString("types") ); 
    } 

} 


