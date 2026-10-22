package com.example.B_ioccontainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // Automatically manages the object of the class
public class OrderService {
    // -----Field Injection ------//
    // @Autowired
    PaymentService paymentService ;

    // -----Constructor Injection ------//
    @Autowired // Inject dependency of payment in order
    //Single object , hence autowired is optional
    public OrderService(@Qualifier("cardPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //----Setter Injection ------//
//    @Autowired
//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }

    void order(){
        paymentService.pay();
        System.out.println("Order Placed");
    }
}
