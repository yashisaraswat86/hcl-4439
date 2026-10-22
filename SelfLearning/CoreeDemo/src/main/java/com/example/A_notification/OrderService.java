package com.example.A_notification;

public class OrderService {
    NotificationService notification ;

    public OrderService(NotificationService notification) {
        this.notification = notification;
    }

    public void order(){
        System.out.println("Order Placed");
        notification.display();
    }
}
