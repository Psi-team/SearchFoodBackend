package com.searchfood.SearchFoodBackend.utils.messages.interfaces; 

import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 

public interface SignUpMailService{ 

    public void sendEmail( SignUpMember signupMember ); 

} 

