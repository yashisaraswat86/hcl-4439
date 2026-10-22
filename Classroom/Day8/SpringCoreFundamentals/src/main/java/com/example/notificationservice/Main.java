package com.example.notificationservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class) ;
        Student stu = ctx.getBean(Student.class);
        Student stu1 = ctx.getBean(Student.class);
        System.out.println(stu.hashCode());
        System.out.println(stu1.hashCode());
        //stu.notifyStudent();
        ctx.close();
    }
}
