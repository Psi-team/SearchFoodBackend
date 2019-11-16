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
           .andExpect( MockMvcResultMatchers.jsonPath("$.username").value(members.getUsername()) ); // problem here.  
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

} 

