package com.searchfood.SearchFoodBackend.properties; 

import org.springframework.boot.context.properties.ConfigurationProperties; 
import org.springframework.stereotype.Component; 

@Component 
@ConfigurationProperties(prefix="search.results") 
public class SearchPageSizeProperties{ 

    private int pageSize; 

    public SearchPageSizeProperties(){ 
        
    } 

    public void setPageSize( int s ){ 
        this.pageSize = s; 
    } 

    public int getPageSize(){ 
        return this.pageSize; 
    } 

} 

