package com.example.B_ioccontainer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary // Priority
@Qualifier("up") // naming bean , this can be passed
@Component // Automatically manages the object of the class
public class UPIPayment implements PaymentService {
    @Override
    public void pay() {
        System.out.println("Pay using upi");
    }
}
