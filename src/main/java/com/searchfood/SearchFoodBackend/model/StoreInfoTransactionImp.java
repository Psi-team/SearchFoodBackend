package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.stereotype.Repository; 

import com.searchfood.SearchFoodBackend.model.interfaces.StoreInfoTransactionInterface; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

@Repository 
public class StoreInfoTransactionImp implements StoreInfoTransactionInterface{ 

    private StoreInfoImp storeInfoImp; 

    @Autowired 
    public StoreInfoTransactionImp( StoreInfoImp s ){ 
        this.storeInfoImp = s; 
    } 

    @Override 
    @Transactional(rollbackFor=Exception.class) 
    // @Transactional預設RunTimeException與非受檢例外會rollback, 受檢例外則不rollback, 透過以下參數更改為受檢例外也會rollback
    // 不能使用在private方法上,因為其不能不繼承, 
    // transactional只會作用在外部呼叫,class內的呼叫無法作用  
    // ref: https://stackoverflow.com/questions/39096860/roll-back-a-if-b-goes-wrong-spring-boot-jdbctemplate
    //      https://www.journaldev.com/2603/spring-transaction-management-jdbc-example#spring-transaction-management-8211-database-setup
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ){ 
        return this.storeInfoImp.createNewStoreInfoToDatabase( storeInfo, username );  
    } 

} 

