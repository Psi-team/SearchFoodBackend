// This interface is to check whether the specified data has already existed in tables. 
package com.searchfood.SearchFoodBackend.utils; 

public interface FindDataITF{ 


    public int isExist(); // define an abstract function that return the id of data in table or -1 for not found. 

} 

