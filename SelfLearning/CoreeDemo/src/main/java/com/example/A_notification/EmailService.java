package com.example.A_notification;

public class EmailService implements NotificationService {
    @Override
    public void display() {
        System.out.println("Email Notification");
    }
}
