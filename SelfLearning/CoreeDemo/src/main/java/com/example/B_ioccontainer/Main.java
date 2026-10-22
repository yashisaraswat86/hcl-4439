package com.example.B_ioccontainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //This starts a spring container using annotation based configuration
        //Appconfig.class -> it is a reflection which contains the meta data of the class
        OrderService orderService = ctx.getBean(OrderService.class) ;
        //getBean -> gives the bean of the specified reflection
        orderService.order();
        User user = ctx.getBean(User.class) ;
        System.out.println(user.getName()); ;

    }
}
