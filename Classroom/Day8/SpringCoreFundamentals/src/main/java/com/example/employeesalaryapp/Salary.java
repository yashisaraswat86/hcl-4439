package com.example.employeesalaryapp;

public class Salary {
    private int basic ;
    private int hra ;

    public Salary(int basic, int hra) {
        this.basic = basic;
        this.hra = hra;
    }

    public int getBasic() {
        return basic;
    }

    public int getHra() {
        return hra;
    }


}
