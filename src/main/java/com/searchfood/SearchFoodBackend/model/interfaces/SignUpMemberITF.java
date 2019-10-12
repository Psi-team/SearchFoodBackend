// This file is to regist a new member and INSERT the new member into the table SearchFood.Users. 
package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 

public interface SignUpMemberITF{ 
    public TokenRecords saveToUsers( SignUpMember signupmember ); // write the new member's data and create token in Token then return that token. 
} 


