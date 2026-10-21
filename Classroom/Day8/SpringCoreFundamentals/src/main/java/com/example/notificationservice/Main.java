package com.example.notificationservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class) ;
        Student stu = ctx.getBean(Student.class);
        stu.notifyStudent();
    }
}
