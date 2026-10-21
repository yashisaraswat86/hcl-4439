package com.example.notificationservice;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WattsappService implements NotificationService{
    @Override
    public void senMsg(String msg) {
        System.out.println("Notification from wattsapp" + msg);
    }
}
