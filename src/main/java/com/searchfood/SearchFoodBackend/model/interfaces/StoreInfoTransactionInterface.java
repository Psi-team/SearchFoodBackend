package com.searchfood.SearchFoodBackend.model.interfaces; 

import org.springframework.transaction.annotation.Transactional; 

import com.searchfood.SearchFoodBackend.model.StoreInfoImp; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

public interface StoreInfoTransactionInterface{ 
    
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ); 

} 

