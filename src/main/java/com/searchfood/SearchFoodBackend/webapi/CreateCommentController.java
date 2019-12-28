package com.searchfood.SearchFoodBackend.webapi; 

import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RequestPart; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartRequest;   
//import org.springframework.util.MultiValueMap; 

import org.springframework.http.HttpStatus; 

import javax.validation.Valid;  
import org.springframework.validation.Errors;  

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.model.data.Comments; 
import com.searchfood.SearchFoodBackend.model.CommentsImp; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataFailToSavedException; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="createComment") 
public class CreateCommentController{ 

    final static private Logger log = LoggerFactory.getLogger( CreateCommentController.class ); 
    private CheckTokensController checkTokensController; 
    private CommentsImp commentsImp; 

    @Autowired 
    public CreateCommentController( CheckTokensController c, CommentsImp ci ){ 
        this.checkTokensController = c; 
        this.commentsImp = ci; 
    } 

    @PostMapping 
    @ResponseStatus( HttpStatus.CREATED ) 
    public void createComments( @RequestHeader("Authorization") String token, 
                                //@Valid @RequestBody Comments commentData, 
                                //Errors errors 
                                @RequestParam("pic") MultipartFile pics, 
                                @RequestParam("star") Float star, 
                                @RequestParam("comments") String comments,  
                                @RequestParam("storeId") int storeId ){ 

        log.debug("Checking the token is valid or not..."); 
        // checking the token is valid or expired. 
        String username = checkTokensController.check( token ); 
        log.info("Valid token"); 

        log.info( "Test : " ); 
        log.info( " " + storeId ); 
        log.info( " " + comments ); 
        log.info( " " + star ); 

        /* 
        log.debug("Checking data is valid or not ..."); 
        if( errors.hasErrors() ){ 
            throw new InvalidDataException( errors ); 
        } 
        */ 

        //log.debug("storeId: " + commentData.getStoreId()); 

        log.debug("saving comment.."); 
        /* 
        if( !commentsImp.saveComments( commentData, username, null ) ) 
            throw new DataFailToSavedException("Data cannot be stored in table."); 
        log.debug("Comment saved.."); 
        */ 

    } 

} 

