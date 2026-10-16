package com.example.employeesalaryapp;

public class Employee {
    private Address address ;
    private Department dept ;
    private Salary salary ;
    public Employee(Address address , Department dept , Salary salary){
        this.address = address ;
        this.dept = dept ;
        this.salary = salary ;
    }
    public void getEmpAddress(){
        address.getAddress();
    }
    public int  getEmpId(){
        return dept.getId() ;
    }
    public String getEmpName(){
        return dept.getName();
    }
    public String  getEmpDept(){
        return dept.getDept() ;
    }
    public int  getEmpBasic(){
        return salary.getBasic() ;
    }
    public int  getHra(){
        return salary.getHra() ;
    }
    public void displayEmployee() {
        System.out.println("Address: ");
        getEmpAddress();
        System.out.println("Employee ID: " + getEmpId());
        System.out.println("Name :" + getEmpName());
        System.out.println("Department: " + getEmpDept());
        System.out.println("Basic Salary: " +  getEmpBasic());
        System.out.println("HRA: " + getHra());
        System.out.println("Total Salary " + getEmpBasic() + getHra());
    }
}
