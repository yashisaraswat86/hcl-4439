package com.example.C_circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    @Autowired // Circular dependency is resolved using feild injection and setter injection
    OrderService orderService ;

//    public PaymentService(OrderService orderService) {
//        this.orderService = orderService;
//    }

    void  pay(){
        System.out.println("Payment Done");
    }

    void place(){
        orderService.details();
        System.out.println("Order Made");

    }
}
