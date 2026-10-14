package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args){
        List<Employee> employee = new ArrayList<>() ;
        employee.add(new Employee(100, "Rahul", "Manager", 10000));
        employee.add(new Employee(101, "Aman", "Accountant", 50000));
        employee.add(new Employee(102, "Priya", "Admin", 80000));
        employee.add(new Employee(103, "Anaya", "Manager", 120000));
        Map<String, Integer> deptSalarySum = employee.stream().collect(Collectors.groupingBy(e -> e.dept, Collectors.summingInt(e -> e.salary)));

        deptSalarySum.forEach((dept, sum) ->
                System.out.println("Department: " + dept + " | Total Salary: " + sum)
        );
    }
}
