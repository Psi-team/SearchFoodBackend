package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.stereotype.Service; 

import com.searchfood.SearchFoodBackend.model.data.Comments; 
import com.searchfood.SearchFoodBackend.model.CommentsImp; 

import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.SignUpMemberImp; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
import com.searchfood.SearchFoodBackend.model.StoreInfoImp; 

@Service 
public class TransactionManagement{ 

    private StoreInfoImp storeInfoImp; 
    private CommentsImp commentsImp; 
    private SignUpMemberImp signupImp; 

    @Autowired 
    public TransactionManagement( StoreInfoImp s, CommentsImp c, SignUpMemberImp su ){ 
        this.signupImp = su; 
        this.commentsImp = c; 
        this.storeInfoImp = s; 
    } 

    @Transactional(rollbackFor=Exception.class)  
    public TokenRecords saveToUsers( SignUpMember s ){ // create new member transaction. 
        return signupImp.saveToUsers( s ); 
    } 
    
    @Transactional(rollbackFor=Exception.class) 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ // create new store transaction. 
        return this.storeInfoImp.createNewStoreInfoToDatabase( storeInfo, username );  
    } 

    @Transactional(rollbackFor=Exception.class) 
    public boolean saveComments( Comments comments, String username, String picUrl ){ // create new comment transaction. 
        return commentsImp.saveComments( comments, username, picUrl ); 
    } 

    // @Transactional預設RunTimeException與非受檢例外會rollback, 受檢例外則不rollback, 透過以下參數更改為受檢例外也會rollback
    // 不能使用在private方法上,因為其不能不繼承, 
    // transactional只會作用在外部呼叫,class內的呼叫無法作用  
    // ref: https://stackoverflow.com/questions/39096860/roll-back-a-if-b-goes-wrong-spring-boot-jdbctemplate
    //      https://www.journaldev.com/2603/spring-transaction-management-jdbc-example#spring-transaction-management-8211-database-setup
    //      https://www.cnblogs.com/harrychinese/p/SpringBoot_jdbc_transaction.html?fbclid=IwAR2ooBRD9FR30NylbnvfMK3m80VinEIKzqX_lvu0lK9f6IRSZAwZOXE3c4Q
    
} 

