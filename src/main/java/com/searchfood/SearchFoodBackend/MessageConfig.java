package com.searchfood.SearchFoodBackend;

// the destination of jms 
import org.apache.activemq.artemis.jms.client.ActiveMQQueue; 
//jms 
import javax.jms.Destination; 
// Annotation 
import org.springframework.context.annotation.Configuration; 
import org.springframework.context.annotation.Bean; 

@Configuration // anntate this class is a configuration that will create beans by container. 
public class MessageConfig{ 

    @Bean 
    public Destination testMessages(){ 
        return new ActiveMQQueue( "tvjs168@gmail.com" ); 
    } 

} 


