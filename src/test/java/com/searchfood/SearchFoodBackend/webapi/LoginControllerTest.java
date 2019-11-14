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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;//.jsonPath; 
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;//.status; 
import org.mockito.BDDMockito; 
import org.springframework.boot.test.context.SpringBootTest; 
import static org.springframework.http.MediaType.APPLICATION_JSON; 

@RunWith( SpringRunner.class ) 
@WebMvcTest( LoginController.class ) 
public class LoginControllerTest{ 

    @Autowired 
    private MockMvc mvc; 

    @MockBean 
    private LoginController loginController; 

    @Test 
    public void login() throws Exception{ 

        Members members = new Members(); 
        TokenRecordsImp tokenImp; 
        TokenRecords token = new TokenRecords(); 
        token = tokenImp.saveTokenTable( members ); 

        BDDMockito.given( loginController.login(members)).willReturn(token); 

        mvc.perform( 
                (MockMvcRequestBuilders.post( "/login" ))
                .content( this.asJsonString( new Members( "Admin@test.com", "1234567", "Chrome" ) ) ) 
                .contentType( APPLICATION_JSON ) 
           ) 
           .andExpect( MockMvcResultMatchers.status().isOk() )
           .andExpect( MockMvcResultMatchers.jsonPath("$.token").exists() );  
    } 

    public static String asJsonString( final Object obj ){ 
        try{ 
            return new ObjectMapper().writeValueAsString( obj ); 
        } catch ( Exception e ){ 
            throw new RuntimeException(e); 
        } 
    } 

} 

