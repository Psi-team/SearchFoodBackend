package com.searchfood.SearchFoodBackend.model; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.stereotype.Service; 

import com.searchfood.SearchFoodBackend.model.interfaces.SignUpMemberTransactionInterface; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.SignUpMemberImp; 

@Service 
public class SignUpMemberTransactionImp implements SignUpMemberTransactionInterface{ 
    
    private SignUpMemberImp signupImp; 

    @Autowired 
    public SignUpMemberTransactionImp( SignUpMemberImp s ){ 
        this.signupImp = s; 
    } 

    @Override 
    @Transactional(rollbackFor=Exception.class)  
    public TokenRecords saveToUsers( SignUpMember s ){ 
        return signupImp.saveToUsers( s ); 
    } 

} 

