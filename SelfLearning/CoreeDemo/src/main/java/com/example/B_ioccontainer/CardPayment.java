package com.example.B_ioccontainer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier // Both qualifies
@Component // Automatically manages the object of the class
public class CardPayment implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Pay using card");
    }
}
