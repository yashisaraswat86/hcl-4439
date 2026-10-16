package com.example.employeesalaryapp;

public class Department {
    private int id ;
    private String dept ;
    private String name ;

    public Department(int id, String dept , String name) {
        this.id = id;
        this.dept = dept;
        this.name = name ;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name ;
    }
    public String getDept() {
        return dept;
    }

}
