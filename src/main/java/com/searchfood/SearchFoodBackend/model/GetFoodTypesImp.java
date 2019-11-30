// 可以考慮把這個model設定成預設的application properties. 在啟動的時候,自動設定完成 
package com.searchfood.SearchFoodBackend.model; 

import org.springframework.stereotype.Repository; 
import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.dao.DataAccessException; 
import org.springframework.dao.EmptyResultDataAccessException; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

// org.json.JSONObject 無法用ResponseEntity<>傳送, 因此只好用Map來包裝直接傳送回去 
//import org.json.JSONArray; 
//import org.json.JSONObject; 

import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.function.Consumer; 
import java.lang.StringBuilder; 

import com.searchfood.SearchFoodBackend.model.data.Referenced; 

@Repository 
public class GetFoodTypesImp{ 

    private static final Logger log = LoggerFactory.getLogger( GetFoodTypesImp.class ); 
    private Referenced foodTypes;
    private JdbcTemplate jdbc; 
    private List<Referenced> foodTypesList; 

    @Autowired 
    public GetFoodTypesImp( JdbcTemplate jdbc ){ 
        this.jdbc = jdbc; 
    } 

    public Map<String,List<String>> getFoodTypesMap(){ 
        log.debug("start to get data..."); 
        Map<String,List<String>> results = getQueryResults(); 
        log.debug("Finished"); 
        return results; 
    } 

    private Map<String,List<String>> getQueryResults(){ 
        log.debug("query data from database in the form of List..."); 
        this.foodTypesList = 
                    jdbc.query( 
                            "SELECT * FROM ReferedTable WHERE class='FoodTypes';", 
                            this::getFoodTypes 
                    ); 
        log.debug("Start to change List to JSONArray..."); 
        return toMap(this.foodTypesList); 
    } 

    private Map<String,List<String>> toMap( List target ){ 

        List<String> Rice = new ArrayList(); 
        List<String> Noodles = new ArrayList(); 
        List<String> FastFood = new ArrayList(); 

        log.debug("forEach..."); 
        target.forEach( 
                new Consumer<Referenced>(){ 
                    @Override 
                    public void accept( Referenced target_ ){ 
                        switch( target_.getTypes() ){ 
                            case "飯": 
                                Rice.add( target_.getValue() ); 
                                break; 
                            case "麵食": 
                                Noodles.add( target_.getValue() ); 
                                break; 
                            case "速食": 
                                FastFood.add( target_.getValue() ); 
                                break; 
                        } 
                    } 
                } 
        ); 

        Map<String,List<String>> resultMap = new HashMap(); 
        resultMap.put("飯",Rice); 
        resultMap.put("麵食",Noodles); 
        resultMap.put("速食",FastFood); 
        log.debug("resultMap " + resultMap); 
        return resultMap; 
    } 

    private Referenced getFoodTypes( ResultSet rs, int rowNum ) throws SQLException{ 
        return new Referenced( 
                rs.getInt("id"), 
                rs.getString("value"), 
                rs.getString("types"),
                rs.getString("class") ); 
    } 

    public List<Integer> getFoodIdsList( List<String> queryValue ){ 
        List<Integer> foodIdList = new ArrayList(); 
        /* 需要更好的搜尋法 */ 
        for( int i = 0; i < queryValue.size(); i++ ){ 
            for( int j = 0; j < this.foodTypesList.size(); j++ ){ 
                if ( this.foodTypesList.get(j).getValue().equals( queryValue.get(i) ) ){ 
                    foodIdList.add( this.foodTypesList.get(j).getId() ); 
                } 
            }
        } 
        log.info( "foodIdList: " + foodIdList ); 
        return foodIdList; 
    } 

    public String getFoodIdsListInString( List<String> queryValue ){ 
        // using StringBuilder to concate String. 
        StringBuilder stringBuilder = new StringBuilder("$"); 
        /* 需要更好的搜尋法 */ 
        for( int i = 0; i < queryValue.size(); i++ ){ 
            for( int j = 0; j < this.foodTypesList.size(); j++ ){ 
                if ( this.foodTypesList.get(j).getValue().equals( queryValue.get(i) ) ){ 
                    stringBuilder.append(String.valueOf(this.foodTypesList.get(j).getId())); 
                    stringBuilder.append("$"); 
                } 
            }
        } 
        log.debug( "stringBuilder: " +stringBuilder.toString() ); 
        return stringBuilder.toString(); 
    } 
} 


