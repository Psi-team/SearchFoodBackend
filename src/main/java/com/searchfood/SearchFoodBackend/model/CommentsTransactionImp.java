package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.stereotype.Service; 

import com.searchfood.SearchFoodBackend.model.data.Comments; 

@Service 
public class CommentsTransactionImp{ 

    private CommentsImp commentsImp; 

    @Autowired 
    public CommentsTransactionImp( CommentsImp cI ){ 
        this.commentsImp = cI; 
    } 

    @Transactional(rollbackFor=Exception.class) 
    public boolean saveComments( Comments comments, String username, String picUrl ){ 
        return commentsImp.saveComments( comments, username, picUrl ); 
    } 

} 

