package com.searchfood.SearchFoodBackend.utils.exceptions; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.ResponseBody; 

import com.searchfood.SearchFoodBackend.utils.exceptions.ErrorResponse; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

import java.util.List; 
import java.util.LinkedList; 

@RestControllerAdvice // @ControllerAdvice + @ResponseBody 
public class ControllerException{ 
    
    @ExceptionHandler( NotFoundException.class ) // data is not exist. 
    public ResponseEntity<?> handleNotFoundException( Exception e ){ 
        ErrorResponse errorResponse = new ErrorResponse( e.getMessage() ); // boxing the error message by ErrorResponse. 
        System.out.println("Testing:\n\t In ControllerException."); 
        return new ResponseEntity<>( errorResponse, HttpStatus.UNAUTHORIZED ); 
    } 

    @ExceptionHandler( DataExistException.class ) // data has existed in database. 
    public ResponseEntity<?> handleDataExistException( Exception e ){ 
        ErrorResponse errorResponse = new ErrorResponse( e.getMessage() ); 
        System.out.println("Testing:\n\t In ControllerException."); 
        return new ResponseEntity<>( errorResponse, HttpStatus.CONFLICT ); 
    } 

    /* 
    @ExceptionHandler( InvalidDataException.class ) 
    public ResponseEntity<?> handleInvalidDataException( InvalidDataException e ){ 
    
        List<FieldError> errors = e.getErrors().getFieldError(); 

        for ( FieldError error : errors ){ 
            System.out.println( error.getObjectName() + " - " + error.getDefaultMessage() );  
        } 

        InvalidDataException invalidDataException = new InvalidDataException( e.getMessage(), listError ); 
        return new ResponseEntity<>( invalidDataException, HttpStatus.BAD_REQUEST ); 
    
        return null; 
    } 
    */ 
    
} 
 
