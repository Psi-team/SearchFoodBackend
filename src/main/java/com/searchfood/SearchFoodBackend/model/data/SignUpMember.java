package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; // used for checking String.  
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Digits; 
import javax.validation.constraints.Email; 
import javax.validation.constraints.Max; 
import javax.validation.constraints.Min; 

import java.util.Date; 

//@Component 
public class SignUpMember{ 
    
    @NotNull 
    @Email( message="username must be the user's email") 
    private String username; // e-mail by default 

    @NotNull 
    @Size( min=6, max=10, message="The length of Password must be between 6 and 10.") 
    private String passwd; 

    @NotNull 
    @Min(0) 
    @Max(1) 
    @Digits( integer=1, fraction=0, message="0 or 1") 
    private int sexual; 

    @NotNull 
    @Min(1900) 
    @Max(2500) 
    private int birthyear; 

    @Min(0) 
    @Max(160) 
    private int age; 

    private Date date = new Date(); 

    public SignUpMember(){ 
    } 
    
    public SignUpMember(String name, String pass, int sex, int birthyear, int age ){
        this.username = name; 
        this.passwd = pass; 
        this.sexual = sex; 
        this.birthyear = birthyear; 
        this.age = age; 
    } 

    // Setter 
    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setPasswd( String pass ){ 
        this.passwd = pass; 
    } 

    public void setSexual( int sex ){ 
        this.sexual = sex; 
    } 

    public void setBirthyear( int birthyear ){ 
        this.birthyear = birthyear; 
    } 

    public void setAge( int age ){ 
        this.age = age; 
    } 

    // Getter 
    public String getUsername(){ 
        return this.username; 
    } 

    public String getPasswd(){ 
        return this.passwd; 
    } 

    public int getSexual(){ 
        return this.sexual; 
    } 

    public int getBirthyear(){ 
        return this.birthyear; 
    } 

    public int getAge(){ 
        return this.age; 
    } 

    @Override 
    public String toString(){ 
       return String.format( "Username: %s\nPassword: %s\nSexual: %d\nBirthYear: %d\nAge: %d", 
                            this,getUsername(), this.getPasswd(), this.getSexual(), 
                            this.getBirthyear(), this.getAge() ); 
    } 

} 

