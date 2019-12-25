package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.stereotype.Service; 

import org.springframework.jdbc.core.JdbcTemplate; 

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

@Service
public class SearchStore{ 

    private final static Logger log = LoggerFactory.getLogger( SearchStore.class ); 

    public Map<String,Object> getSearchList( ResultSet rs, int rowNum ) throws SQLException{ 

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
        //resultList.put("creator",rs.getString("creator"));

        return resultList; 

    } 

} 

