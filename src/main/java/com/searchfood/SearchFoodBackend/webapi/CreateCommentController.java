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
//import org.springframework.util.MultiValueMap; 

import org.springframework.web.client.RestTemplate; 

import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 

import org.springframework.http.HttpStatus; 

import javax.validation.Valid;  
import org.springframework.validation.Errors;  

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.model.data.Comments; 
import com.searchfood.SearchFoodBackend.utils.CheckTokensController; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.DataFailToSavedException; 
import com.searchfood.SearchFoodBackend.webapi.FilesController; 
//import com.searchfood.SearchFoodBackend.model.CommentsTransactionImp; 
import com.searchfood.SearchFoodBackend.model.TransactionManagement; 

@RestController 
@CrossOrigin("*") 
@RequestMapping(value="createComment") 
public class CreateCommentController{ 

    final static private Logger log = LoggerFactory.getLogger( CreateCommentController.class ); 
    private CheckTokensController checkTokensController; 
    private TransactionManagement transactionManagement;//private CommentsTransactionImp commentsTransactionImp; 
    private RestTemplate restTemplate; 
    private FilesController fileController; 

    @Autowired 
    public CreateCommentController( CheckTokensController c, TransactionManagement tm, RestTemplate restTemplate, FilesController f ){ 
        this.checkTokensController = c; 
        this.transactionManagement = tm; 
        this.restTemplate = restTemplate; // RestTemplate 
        this.fileController = f; 
    } 

    @PostMapping 
    @ResponseStatus( HttpStatus.CREATED ) 
    public void createComments( @RequestHeader("Authorization") String token, 
                                @Valid Comments commentData, // with no @RequestBody, Why? 
                                Errors errors ){ 

        log.debug("Checking the token is valid or not..."); 
        // checking the token is valid or expired. 
        String username = checkTokensController.check( token ); 
        log.info("Valid token"); 

        log.debug("Checking data is valid or not ..."); 
        if( errors.hasErrors() ){ 
            throw new InvalidDataException( errors ); 
        } 

        log.debug("storeId: " + commentData.getStoreId()); 
        log.debug("stars" + commentData.getStar()); 
        log.debug("Comments" + commentData.getComments()); 

        log.debug("Trying to saving picture."); 

        // Using RestTemplate ?????? 
        /* 
        String picUrl = restTemplate.
                postForObject( 
                    ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("uploadFile")
                        .toUriString(), 
                    commentData.getPic(), 
                    String.class ).toString();  
        */ 

        String picUrl = fileController.uploadFileLocation( commentData.getPic() ); 
        if( picUrl == null ) 
            throw new DataFailToSavedException("Picture cannot be stored in table."); 
        
        log.debug("The pic upload sucessfully: " + picUrl ); 
        
        log.debug("saving comment.."); 
    
        if( !transactionManagement.saveComments( commentData, username, picUrl ) ) 
            throw new DataFailToSavedException("Data cannot be stored in table."); 

        log.debug("Comment saved.."); 

    } 

} 

