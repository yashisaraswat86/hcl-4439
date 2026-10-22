package com.example.B_ioccontainer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // tells it is a configuration class
@ComponentScan("com.example.B_ioccontainer") // tells to scan all the components in the packege given
public class AppConfig {
    //Explicitly tell spring to make object for this class
    @Bean
    public User crateUser(){
        return new User(23 , "Ram" );
    }
}
