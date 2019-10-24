package com.searchfood.SearchFoodBackend.utils.exceptions; 

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.ControllerAdvice; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.ResponseBody; 

import org.springframework.validation.FieldError; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

import com.searchfood.SearchFoodBackend.utils.exceptions.ErrorResponse; 
import com.searchfood.SearchFoodBackend.utils.exceptions.InvalidDataException; 
import com.searchfood.SearchFoodBackend.utils.exceptions.NotFoundException; 

import java.util.List; 
import java.util.LinkedList; 

@RestControllerAdvice // @ControllerAdvice + @ResponseBody 
public class ControllerException{ 

    private static final Logger log = LoggerFactory.getLogger( ControllerException.class ); 
    
    @ExceptionHandler( NotFoundException.class ) // data is not exist. 
    public ResponseEntity<?> handleNotFoundException( Exception e ){ 
        ErrorResponse errorResponse = new ErrorResponse( e.getMessage() ); // boxing the error message by ErrorResponse. 
        log.error("warning:\t In ControllerException: NotFoundException"); 
        return new ResponseEntity<>( errorResponse, HttpStatus.UNAUTHORIZED ); 
    } 

    @ExceptionHandler( DataExistException.class ) // data has existed in database. 
    public ResponseEntity<?> handleDataExistException( Exception e ){ 
        ErrorResponse errorResponse = new ErrorResponse( e.getMessage() ); 
        log.error("warning:\t In ControllerException: DataExistException."); 
        return new ResponseEntity<>( errorResponse, HttpStatus.CONFLICT ); 
    } 

    @ExceptionHandler( InvalidDataException.class ) 
    public ResponseEntity<?> handleInvalidDataException( InvalidDataException e ){ // wrong data definition. 
    
        List<FieldError> Errors = e.getErrors().getFieldErrors(); 
        List<InvalidDataResponse> invalidDataResponse = new LinkedList(); 
        for ( FieldError error : Errors ){ 
            invalidDataResponse.add( new InvalidDataResponse(error.getField(), error.getDefaultMessage()) );  
        } 
        log.error("warning:\t In ControllerException: InvalidDataException"); 
        return new ResponseEntity<>( invalidDataResponse, HttpStatus.BAD_REQUEST ); 
    } 
    
} 
 
