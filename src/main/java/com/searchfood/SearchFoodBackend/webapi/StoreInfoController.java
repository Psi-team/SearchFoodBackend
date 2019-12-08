package com.searchfood.SearchFoodBackend.webapi; 

// annotation 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.beans.factory.annotation.Autowired; 
// http. 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
// validate the data. 
import javax.validation.Valid; 
import org.springframework.validation.Errors; 
// user-defined class. 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.model.StoreInfoTransactionImp; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataExistException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.TokenExpiredException; 
// logger 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="createStore",produces="application/json") 
public class StoreInfoController{ 

    private static final Logger log = LoggerFactory.getLogger( StoreInfoController.class ); 
    private StoreInfoTransactionImp storeInfoTransactionImp; 
    private CheckTokensController checkTokensController;  
    private StoreInfo storeInfoData;  

    @Autowired 
    public StoreInfoController( StoreInfoTransactionImp storeInfoTransactionImp, CheckTokensController token ){ 
        this.checkTokensController = token; 
        this.storeInfoTransactionImp = storeInfoTransactionImp; 
    } 

    @PostMapping( consumes="application/json" )  
    public ResponseEntity<?> createNewStoreInfo( @Valid @RequestBody StoreInfo storeInfo, 
            Errors errors, @RequestHeader("Authorization") String token ){ 

        log.debug( "storename: " + storeInfo.getStorename() ); 
        log.debug( "latlong: " + storeInfo.getLatLong() ); 
        log.debug( "tags: " + storeInfo.getTags() ); 

        // checking the token is valid or expired. 
        String username = checkTokensController.check( token ); 
        log.info("Valid token"); 
        
        // check the data whether valid or not. 
        if ( errors.hasErrors() ){ 
            log.warn( "The data for build new store infomation is invalid." );  
            throw new InvalidDataException( errors ); 
        } 
        log.info( "The data for building new store infomation is valid." );  

        if ( (storeInfoData  = storeInfoTransactionImp.createNewStoreInfoToDatabase( storeInfo, username )) != null ){ 
            log.info( username + " has created the new store info " + storeInfo.getStorename() + "." ); 
            return new ResponseEntity<>( storeInfoData, HttpStatus.CREATED ); 
        } 

        throw new DataExistException( storeInfo.getStorename() + " has existed in the table StoreInfo." ); 
    } 

} 


