package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 

public interface StoreInfoITF{ 
    public StoreInfo createNewStoreInfoToDatabase( StoreInfo storeInfo, String username ); 
} 

