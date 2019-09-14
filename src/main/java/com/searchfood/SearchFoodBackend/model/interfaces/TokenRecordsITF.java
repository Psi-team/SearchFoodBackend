// An interface that define what TokenRecordsImp to do and produces a token and write token and username to Database. 
package com.searchfood.SearchFoodBackend.model.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.data.Members; 

public interface TokenRecordsITF{ 

    //public boolean isExist( String username );  // to Check the client whether exist in tables Users.

    public TokenRecords saveTokenTable( Members mem ); // save data to table Token and return a token to frontend 

} 


// It is highly recommanded that using JPA. The interface that extends CrudRepository<,> 
// The doc of JPA: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/?fbclid=IwAR1Ru6MZ3eWjuSnXqypSVkg45UeznSiSg-1adIDURxTOOCUEKsWoVFPcMcs#repositories.core-concepts

