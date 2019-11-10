package com.searchfood.SearchFoodBackend.webapi; 

// annotation 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.beans.factory.annotation.Autowired; 
// http. 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
// validate the data. 
import javax.validation.Valid; 
import org.springframework.validation.Errors; 
// user-defined class. 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.CheckTokenImp; 
import com.searchfood.SearchFoodBackend.model.StoreInfoImp; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 
// logger 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="createStore",produces="application/json") 
public class StoreInfoController{ 

    private static final Logger log = LoggerFactory.getLogger( StoreInfoController.class ); 
    private StoreInfoImp storeInfoImp; 
    private CheckTokenImp checkToken;  
    private StoreInfo storeInfoData;  

    @Autowired 
    public StoreInfoController( StoreInfoImp storeInfoImp, CheckTokenImp token ){ 
        this.checkToken = token; 
        this.storeInfoImp = storeInfoImp; 
    } 

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED) 
    public StoreInfo createNewStoreInfo( @Valid @RequestBody StoreInfo storeInfo, 
            Errors errors, @RequestHeader("Authorization") String token ){ 

        String username; 

        log.debug( "storename: " + storeInfo.getStorename() ); 
        log.debug( "lat_long: " + storeInfo.getLat_long() ); 

        // checking the token is valid or not. 
        if( (username = checkToken.check(token) ) == null ){ // if the token doesn't exist in database. 
            log.warn( "The token is invalid." ); 
            throw new NotFoundException("The token is invalid."); 
        } 
        log.info( username + " is trying to create new store info." ); 
        
        // check the data whether valid or not. 
        if ( errors.hasErrors() ){ 
            System.out.println(errors); 
            log.warn( "The data for build new store infomation is invalid." );  
            throw new InvalidDataException( errors ); 
        } 
        log.info( "The data for building new store infomation is valid." );  

        if ( (storeInfoData  = storeInfoImp.createNewStoreInfoToDatabase( storeInfo, username )) != null ){ 
            log.info( username + " has created the new store info " + storeInfo.getStorename() + "." ); 
            return storeInfoData; 
        } 

        throw new DataExistException( storeInfo.getStorename() + " has existed in the table StoreInfo." ); 
    } 

} 


