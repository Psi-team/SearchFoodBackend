// An interface that define what TokenRecordsImp to do and produces a token and write token and username to Database. 
package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 

public interface TokenRecordsITF{ 

    public boolean isExist( String username );  // to Check the client whether exist in tables Users.

    public TokenRecords saveTokenTable( TokenRecords token ); // save data to table Token and return a token to frontend 

} 

