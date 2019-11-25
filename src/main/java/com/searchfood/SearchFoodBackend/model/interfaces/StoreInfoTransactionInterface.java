package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

public interface StoreInfoTransactionInterface{ 
    
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ); 

} 

