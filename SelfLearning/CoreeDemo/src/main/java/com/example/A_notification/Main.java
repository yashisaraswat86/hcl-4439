package com.example.A_notification;


// IOC is a principle or idea
// DI is a approach to acheive IOC injection

public class Main {
    public static void main(String[] args) {
        //Main is used here to build dependency
        //Else everything was tightly coupled and a class was dependent on another class and making another class object
        NotificationService notificationService = new PopUpService();
        //Dependency injection can happen through constructor , setter , field
        OrderService orderService = new OrderService(notificationService) ;
        orderService.order();
    }
}