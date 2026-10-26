package com.example.C_circulardependency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    @Autowired
    PaymentService paymentService ;

//    public OrderService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }

    void order(){
        paymentService.pay();
        System.out.println("Order Placed");
    }

    void details(){
        System.out.println("Order details");
    }
}
