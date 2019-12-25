package com.searchfood.SearchFoodBackend.utils.messages.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.Emails; 

public interface ResetPasswordMailService{ 
    public void sendEmail( Emails email, String username, String password ); 
} 


