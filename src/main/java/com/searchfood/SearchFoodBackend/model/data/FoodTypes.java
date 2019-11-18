package com.searchfood.SearchFoodBackend.model.data; 

import java.util.Map; 
import java.util.HashMap; 
import java.util.List; 
import java.util.ArrayList; 

public class FoodTypes{ 

    private List<String> rice = new ArrayList(); 
    private List<String> noodles = new ArrayList(); 
    private List<String> fastfood = new ArrayList(); 

    public FoodTypes(){ 
        // 飯食 
        rice.add("炒飯"); 
        rice.add("燴飯"); 
        rice.add("滷肉飯"); 
        rice.add("豬排飯"); 
        rice.add("三寶飯"); 

        // 麵食 
        noodles.add("油麵"); 
        noodles.add("義大利麵"); 
        noodles.add("陽春麵"); 
        noodles.add("粄條"); 
        noodles.add("白麵"); 
        noodles.add("拉麵"); 

        // 速食 
        fastfood.add("麥當勞"); 
        fastfood.add("肯德基"); 
        fastfood.add("頂呱呱"); 
        fastfood.add("胖老爹"); 
    } 

    // add new foods. 
    public void addRice( String s ){ 
        this.rice.add(s); 
    } 

    public void addNoodles( String s ){ 
        this.noodles.add(s); 
    } 

    public void addFastFood( String s ){ 
        this.fastfood.add(s); 
    } 

    // get foods. 
    public List getRice(){ 
        return this.rice; 
    } 

    public List getNoodles(){ 
        return this.noodles; 
    } 

    public List getFastFood(){ 
        return this.fastfood; 
    } 

} 

