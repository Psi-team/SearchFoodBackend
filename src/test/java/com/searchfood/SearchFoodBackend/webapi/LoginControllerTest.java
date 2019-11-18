package com.searchfood.SearchFoodBackend.webapi; 
// user defined 
import com.searchfood.SearchFoodBackend.webapi.LoginController; 
import com.searchfood.SearchFoodBackend.model.data.Members; 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.TokenRecordsImp; 
// test 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; 
import org.springframework.boot.test.mock.mockito.MockBean; 
import org.springframework.test.context.junit4.SpringRunner; 
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders; 
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;  
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;//.jsonPath; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;//.status; 
import org.mockito.BDDMockito; 
import org.springframework.boot.test.context.SpringBootTest; 
import static org.springframework.http.MediaType.APPLICATION_JSON; 
import static org.hamcrest.collection.IsCollectionWithSize.hasSize; 

import org.json.JSONObject; 
import com.fasterxml.jackson.databind.ObjectMapper; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.HttpStatus; 

@RunWith( SpringRunner.class ) 
@WebMvcTest( LoginController.class ) 
public class LoginControllerTest{ 

    @Autowired 
    private MockMvc mvc; 

    @MockBean 
    private LoginController loginController; 

    @Test 
    public void login() throws Exception{ 

        Members members = new Members( "Admin@test.com", "1234567", "Chrome" ); 
        TokenRecords token = new TokenRecords(); 

        //BDDMockito.given( loginController.login(members) ).willReturn( new ResponseEntity<TokenRecords>(token,HttpStatus.OK) ); 

        mvc.perform( 
                (MockMvcRequestBuilders.post( "/login" ))
                .contentType( APPLICATION_JSON ) 
                .content( this.asJsonString( members ) )
                .characterEncoding("utf-8") // need to add this statements or the request body is not set. 
           ) 
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andDo( MockMvcResultHandlers.print() )
           //.andExpect( MockMvcResultMatchers.jsonPath("$.username").value(members.getUsername()) ); // problem here.  
           ; 
    } 

    public static String asJsonString( final Object obj ){ 
        try{ 
            String mapper = new ObjectMapper().writeValueAsString( obj ); 
            System.out.println("Content: "+mapper); 
            return mapper; 
        } catch ( Exception e ){ 
            throw new RuntimeException(e); 
        } 
    } 

    /* ref: 
     *      https://www.blazemeter.com/blog/spring-boot-rest-api-unit-testing-with-junit/?fbclid=IwAR08UwXqiAGfW5pQSjBh63EfkjahZzXJ0d1ogfKigt8TjPCMVA6Z9z4KBRE
     *      https://blog.csdn.net/koflance/article/details/63262484?fbclid=IwAR1BXiHn2suSojBE_sxb_Rj3daUBCZGSbqZmaKGpFv3NP96LVP2o6wYG3OU
     *      https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/?fbclid=IwAR0NdOEHbnp8s772AtwA15ZGKHna_6kiPJkkEtrO0hwmkg6fLh0Jks_anj0
     *      https://stackoverflow.com/questions/18336277/how-to-check-string-in-response-body-with-mockmvc?fbclid=IwAR0Uim0O2BLO-sYMZYgHeK6Blt5X_fa4PRDxMis6Ul6mL5aNzuELfqb9aSU
     *      https://www.cnblogs.com/0201zcr/p/5756642.html?fbclid=IwAR2pISxyRs0oGkS3iTJiBC2pMpR3wS4gsrqBBm7XZFLNb5PXEaK50Q6c5xw
     */ 
} 

