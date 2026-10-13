package Streams;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args){
        List<Employee> employee = new ArrayList<>() ;
        employee.add(new Employee(100, "Rahul", "Manager", 10000));
        employee.add(new Employee(101, "Aman", "Accountant", 50000));
        employee.add(new Employee(102, "Priya", "Admin", 80000));
        employee.add(new Employee(103, "Anaya", "Manager", 120000));
        employee.stream().filter(e -> e.name.startsWith("A"))
                .map(e -> e.name.toUpperCase())
                .sorted().forEach(System.out :: println);
    }
}
