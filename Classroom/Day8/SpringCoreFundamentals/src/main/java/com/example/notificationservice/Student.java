package com.example.notificationservice;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Student {
    private NotificationService notificationService ;
    @Autowired
    public Student(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    public void notifyStudent(){
        notificationService.senMsg("Your assessment is sheduled");
    }
    @PostConstruct
    public void init(){
        System.out.println("Object created");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Object destroyed");
    }
}
