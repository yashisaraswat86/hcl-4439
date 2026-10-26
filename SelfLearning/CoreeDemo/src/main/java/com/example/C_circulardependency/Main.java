package com.example.C_circulardependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCongif.class);
        OrderService orderService = ctx.getBean(OrderService.class);
        PaymentService paymentService = ctx.getBean(PaymentService.class) ;
        orderService.order();
        paymentService.place();
    }
}
