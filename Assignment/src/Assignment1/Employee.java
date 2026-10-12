package Assignment1;

import java.util.*;

class Employee {
    int Emp_id;
    String Name;
    String Role;
    double HRA;
    double Basic;

    public Employee(int emp_id, String name, String role, double hra, double basic) {
        Emp_id = emp_id;
        Name = name;
        Role = role;
        HRA = hra;
        Basic = basic;
    }

    public double calculateBonus() {
        double bonusPer;

        switch (Role.toLowerCase()) {
            case "manager":
                bonusPer = 0.12;
                break;
            case "admin":
                bonusPer = 0.10;
                break;
            case "engineer":
                bonusPer = 0.12;
                break;
            case "accountant":
                bonusPer = 0.08;
                break;
            default:
                bonusPer = 0.0;
        }

        return (Basic + HRA) * bonusPer;
    }

    public void display() {
        System.out.println("Emp_id: " + Emp_id);
        System.out.println("Name: " + Name);
        System.out.printf("Bonus: Rs. %.0f%n", calculateBonus());
    }


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(100, "Rahul", "Manager", 10000, 200000));
        employees.add(new Employee(101, "Aman", "Accountant", 5000, 100000));
        employees.add(new Employee(102, "Priya", "Admin", 8000, 150000));
        employees.add(new Employee(103, "Neha", "Engineer", 12000, 180000));

        employees.sort(
                (e1, e2) -> Double.compare(e2.calculateBonus(), e1.calculateBonus())
        );

        for (Employee employee : employees) {
            employee.display();
            System.out.println("------------------------------");
        }
    }

}


