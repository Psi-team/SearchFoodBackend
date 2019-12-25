package com.searchfood.SearchFoodBackend.utils.messages; 

// mail 
import org.springframework.mail.SimpleMailMessage; 
import org.springframework.mail.javamail.JavaMailSender; 
// stereotype 
import org.springframework.stereotype.Service; 
// Autowired
import org.springframework.beans.factory.annotation.Autowired; 
// user-define class 
import com.searchfood.SearchFoodBackend.utils.messages.interfaces.ResetPasswordMailService; 
import com.searchfood.SearchFoodBackend.model.data.Emails; 
// logging import org.slf4j.Logger; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

@Service 
public class SendResetPasswordMailService implements ResetPasswordMailService{ 

    /* Maybe can use the Spring Integration by stmp. */ 

    private JavaMailSender javaMailSender; 
    private static final Logger log = LoggerFactory.getLogger( SendResetPasswordMailService.class ); 
    private SimpleMailMessage msg = new SimpleMailMessage(); 

    @Autowired 
    public SendResetPasswordMailService( JavaMailSender m ){ 
        this.javaMailSender = m; 
    } 

    @Override 
    public void sendEmail( Emails email, String username, String password ){ 
        msg.setTo( email.getEmail() ); 

        msg.setSubject( "Reset password for Search Food." ); 
        msg.setText( String.format( 
                    "Hello, %s:\n\n\n\t We have reset your password: %s\n\t You can login the website then change the password.\n\n\n\n\t\t\t\t\tSincerely, Psi-team SearchFood\n", 
                    username, password 
                    ) ); 

        javaMailSender.send(msg); 
        log.info("Sending email to " + email.getEmail() + "!!!" );  
    } 
    
} 



