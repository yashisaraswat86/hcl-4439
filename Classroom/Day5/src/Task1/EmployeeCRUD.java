package Task1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name
                + ", Salary: " + salary;
    }
}

public class EmployeeCRUD {

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        // CREATE
        employees.add(new Employee(103, "Aman", 40000));
        employees.add(new Employee(101, "Riya", 50000));
        employees.add(new Employee(104, "Rahul", 35000));
        employees.add(new Employee(102, "Neha", 45000));

        System.out.println("Original Employees:");
        employees.forEach(System.out::println);

        // READ: Search employee by ID
        int searchId = 102;
        boolean found = false;

        for (Employee e : employees) {
            if (e.id == searchId) {
                System.out.println("\nEmployee Found:");
                System.out.println(e);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found");
        }

        // UPDATE: Update employee salary
        for (Employee e : employees) {
            if (e.id == 102) {
                e.salary = 60000;
                System.out.println("\nEmployee Updated:");
                System.out.println(e);
                break;
            }
        }

        // DELETE: Remove employee by ID using Iterator
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            Employee e = it.next();

            if (e.id == 104) {
                it.remove();
                System.out.println("\nEmployee Deleted: " + e.name);
                break;
            }
        }

        // ITERATE USING ITERATOR
        System.out.println("\nTraversal using Iterator:");

        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // FORWARD TRAVERSAL USING LISTITERATOR
        System.out.println("\nForward Traversal using ListIterator:");

        ListIterator<Employee> listIterator =
                employees.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // BACKWARD TRAVERSAL USING LISTITERATOR
        System.out.println("\nBackward Traversal using ListIterator:");

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // INCREASING ORDER BY EMPLOYEE ID
        Collections.sort(employees,
                Comparator.comparingInt(e -> e.id));

        System.out.println("\nEmployees in Increasing Order of ID:");

        for (Employee e : employees) {
            System.out.println(e);
        }

        // DECREASING ORDER BY EMPLOYEE ID
        Collections.sort(employees,
                Comparator.comparingInt((Employee e) -> e.id)
                        .reversed());

        System.out.println("\nEmployees in Decreasing Order of ID:");

        for (Employee e : employees) {
            System.out.println(e);
        }

        sc.close();
    }
}

