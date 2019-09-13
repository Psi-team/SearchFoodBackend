package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; 
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Digits; 
import javax.validation.constraints.Email; 

//@Component 
public class SignUpMember{ 
    
    @NotNull 
    @Email( message="username must be the user's email") 
    private String username; // e-mail by default 

    @NotNull 
    @Size( min=6, max=10, message="The length of Password must be between 6 and 10.") 
    private String pass; 

    @NotNull 
    @Size( min=0, max=1, message="Sexual must be male or female.") 
    @Digits( integer=1, fraction=0, message="0 or 1") 
    private int sexual; 

    @NotNull 
    @Digits( integer=4, fraction=0, message="Birthyear INVALID!!") 
    private int birthyear; 

    @Size( min=0, max=150, message="Age INVALID!!") 
    private int age; 

    public SignUpMember(){ 
    } 

    // Setter 
    public void setUsername( String name ){ 
        this.username = name; 
    } 

    public void setPass( String pass ){ 
        this.pass = pass; 
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

    public String getPass(){ 
        return this.pass; 
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

} 

