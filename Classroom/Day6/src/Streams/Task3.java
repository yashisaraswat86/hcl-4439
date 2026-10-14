package Streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args){
        List<Employee> employee = new ArrayList<>() ;
        employee.add(new Employee(100, "Rahul", "Manager", 10000));
        employee.add(new Employee(101, "Aman", "Accountant", 50000));
        employee.add(new Employee(102, "Priya", "Admin", 80000));
        employee.add(new Employee(103, "Anaya", "Manager", 120000));
        employee.stream()
                .sorted(Comparator.comparingInt((Employee e) -> e.salary).reversed())
                .limit(3)
                .forEach(e -> System.out.println(e.salary));


    }
}
