package com.searchfood.SearchFoodBackend.webapi; 

// Annotations 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RequestBody; 
// Autowired 
import org.springframework.beans.factory.annotation.Autowired; 
// a basic abstraction over client/server-side HTTP. 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
// Validation  
import javax.validation.Valid; // Valid whether the bean meets constraints. 
import org.springframework.validation.Errors; // If constraints don't meet, capture the error messages. 
// User-defined class 
import com.searchfood.SearchFoodBackend.model.data.TokenRecords; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember;  
import com.searchfood.SearchFoodBackend.model.TokenRecordsImp; 

@RestController 
@RequestMapping( value="sigup", produces="application/json" ) 
@CrossOrigin("*") 
public class SignUpController{ 

} 

