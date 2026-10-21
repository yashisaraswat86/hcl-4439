package com.example.notificationservice;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements NotificationService{
    @Override
    public void senMsg(String msg) {
        System.out.println("Notification from email" + msg);
    }
}
