package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 

public interface SignUpMemberTransactionInterface{ 
    public TokenRecords saveToUsers( SignUpMember s ); 
} 

