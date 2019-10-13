package com.searchfood.SearchFoodBackend.model.data; 

// This class is used for query from database for temporary used. 

public class tmpData{ 

    private String str1; 
    private String str2; 
    private String str3; 

    public tmpData(){ 

    } 

    public tmpData(String s1, String s2, String s3){ 
        this.str1 = s1; 
        this.str2 = s2; 
        this.str3 = s3; 
    } 

    public tmpData(String s1, String s2){ 
        this.str1 = s1; 
        this.str2 = s2; 
    } 
    
    public tmpData(String s1){ 
        this.str1 = s1; 
    } 

    // getter 
    public String getStr1(){ 
        return this.str1; 
    } 

    public String getStr2(){ 
        return this.str2; 
    } 

    public String getStr3(){ 
        return this.str3; 
    } 

    // setter 
    public void setStr1( String s ){ 
        this.str1 = s; 
    } 

    public void setStr2( String s ){ 
        this.str2 = s; 
    } 

    public void setStr3( String s ){ 
        this.str3 = s; 
    } 

} 

