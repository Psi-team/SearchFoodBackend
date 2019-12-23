package com.searchfood.SearchFoodBackend.model.data; 

// spring 
import org.springframework.stereotype.Component; 

// java 
import javax.validation.constraints.Size; // used for checking String.  
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Email; 
import javax.validation.constraints.Max; 
import javax.validation.constraints.Min; 

@Component 
public class SignUpMember{ 
    
    @NotNull 
    @Email( message="Mail must be the user's email") 
    private String mail; // e-mail by default 

    @NotNull 
    @Size( min=6, max=10, message="The length of Password must be between 6 and 10.") 
    private String passwd; 

    @NotNull
    @Size(message="Username cannot be null.")  
    private String username; 

    @NotNull 
    @Min(0) 
    @Max(1) 
    private int sexual; 

    @NotNull 
    @Min(1900) 
    @Max(2500) 
    private int birthyear; 

    @Min(0) 
    @Max(160) 
    private int age; 

    public SignUpMember(){ 
    } 
    
    public SignUpMember(String mail, String username, String pass, int sex, int birthyear, int age ){
        this.mail = mail; 
        this.username = username; 
        this.passwd = pass; 
        this.sexual = sex; 
        this.birthyear = birthyear; 
        this.age = age; 
    } 

    // Setter 
    public void setMail( String mail ){ 
        this.mail = mail; 
    } 

    public void setUsername( String username ){ 
        this.username = username; 
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
    public String getMail(){ 
        return this.mail; 
    } 

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
       return String.format( "Mail: %s\nUsername: %s\nPassword: %s\nSexual: %d\nBirthYear: %d\nAge: %d", 
                            this.getMail(), this.getUsername(), this.getPasswd(), this.getSexual(), 
                            this.getBirthyear(), this.getAge() ); 
    } 

} 

