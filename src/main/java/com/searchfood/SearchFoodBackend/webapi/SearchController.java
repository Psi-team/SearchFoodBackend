// use @ConfigurationProperties to set the Searching properties
// See PP.122 on Spring in Action. Ch5.2 
package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestParam; //import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import java.util.List; 
import java.util.Map; 
import java.util.HashMap; 

import com.searchfood.SearchFoodBackend.model.SearchStoresImp; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="/Search", produces="application/json") 
public class SearchController{ 

    private static final Logger log = LoggerFactory.getLogger( SearchController.class ); 
    private SearchStoresImp searchStoresImp; 
    private List<Map<String,Object>> resultsList; 

    @Autowired 
    public SearchController(SearchStoresImp searchStore){ 
        this.searchStoresImp = searchStore; 
    } 

    @GetMapping 
    public ResponseEntity<?> getSearchResults( @RequestParam(value="foodType") String foodType, 
                                               @RequestParam(value="city") String city,
                                               @RequestParam(value="district") String district ){ 

        log.debug("foodTypes: " + foodType + ", city: " + city + ", district: " + district ); 
        /* 
        if( city.equals("null") && !district.equals("null") ){ // errors with no city but district only.  
            return new ResponseEntity(HttpStatus.BAD_REQUEST); 
        }else if( foodType.equals("null") && !city.equals("null") ){ // search by location. 
            log.debug("Search by Locations.");  
        }else if( !foodType.equals("null") && city.equals("null") ){ // search by Food. 
            log.debug("Search by Food.");  
        }else if( !foodType.equals("null") && !city.equals("null") ){ // search by food and location. 
            log.debug("Search by food and location.");  
        }else{ 
            return new ResponseEntity(HttpStatus.BAD_REQUEST);  
        } 
        */ 
        if( city.equals("") && !district.equals("") ){ // errors with no city but district only.  

            return new ResponseEntity(HttpStatus.BAD_REQUEST); 

        }else if( foodType.equals("") && !city.equals("") ){ // search by location. 

            log.debug("Search by Locations.");  
            resultsList = searchStoresImp.getSearchByLocation( city, district ); 

        }else if( !foodType.equals("") && city.equals("") ){ // search by Food. 

            log.debug("Search by Food.");  
            resultsList = searchStoresImp.getSearchByFoodType( foodType ); 

        }else if( !foodType.equals("") && !city.equals("") ){ // search by food and location. 

            log.debug("Search by food and location.");  
            resultsList = searchStoresImp.getSearchByFoodTypeWithLocation( foodType, city, district ); 

        }else{ 
            return new ResponseEntity(HttpStatus.BAD_REQUEST);  
        } 

        if ( null != resultsList ) return new ResponseEntity( resultsList, HttpStatus.OK ); 
        else throw new NotFoundException("No suitable results."); 
    } 
} 

