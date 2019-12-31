package com.searchfood.SearchFoodBackend.utils.messages; 

// mail 
import org.springframework.mail.SimpleMailMessage; 
import org.springframework.mail.javamail.JavaMailSender; 
// stereotype 
import org.springframework.stereotype.Service; 
// Autowired
import org.springframework.beans.factory.annotation.Autowired; 
// Async 
import org.springframework.scheduling.annotation.Async; 
// user-define class 
import com.searchfood.SearchFoodBackend.utils.messages.interfaces.SignUpMailService; 
import com.searchfood.SearchFoodBackend.model.data.SignUpMember; 
import com.searchfood.SearchFoodBackend.model.data.StoreInfo; 
// logging import org.slf4j.Logger; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@Service 
public class SendSignUpMailService implements SignUpMailService{ 

    /* Maybe can use the Spring Integration by stmp. */ 

    private JavaMailSender javaMailSender; 
    private static final Logger log = LoggerFactory.getLogger( SendSignUpMailService.class ); 
    private SimpleMailMessage msg = new SimpleMailMessage(); 

    @Autowired 
    public SendSignUpMailService( JavaMailSender m ){ 
        this.javaMailSender = m; 
    } 

    @Override 
    @Async 
    public void sendEmail( SignUpMember signupMember ){ 
        msg.setTo( signupMember.getMail() ); 

        msg.setSubject( "Thanks you to sign up for SearchFood." ); 
        msg.setText( String
                        .format("Hello, %s:\n\n    Thanks for your signing up for SearchFood,\n    Now, you can enjoy your meal.\n    You know what? Damn you!!", 
                                    signupMember.getUsername() ) 
                               ); 

        javaMailSender.send(msg); 
        log.info("Sending email to " + signupMember.getUsername() + "!!!" );  
        log.info("The Thread is " + Thread.currentThread().getName()); 
    } 
    
} 


