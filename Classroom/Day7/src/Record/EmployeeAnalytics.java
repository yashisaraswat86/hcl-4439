package Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalytics{
    record Employee(int id, String name, String dept, BigDecimal salary,
                    LocalDate hireDate, Set<String> skills) {}

    static Employee e(int id, String n, String d, int sal, String date, String... skills) {
        return new Employee(id, n, d, BigDecimal.valueOf(sal),
                LocalDate.parse(date), Set.of(skills));
    }

    public static void main(String[] args) {

        List<Employee> emps = List.of(

                e(1, "Asha",   "Engineering",  90000, "2019-03-01", "Java", "SQL"),
                e(2, "Ravi",   "Engineering", 120000, "2016-07-15", "Java", "Spring", "AWS"),
                e(3, "Meera",  "HR",           60000, "2020-01-10", "Recruiting"),
                e(4, "John",   "Sales",        70000, "2018-11-20", "Negotiation", "CRM"),
                e(5, "Priya",  "Engineering", 105000, "2021-05-05", "Java", "React"),
                e(6, "Karan",  "Sales",        65000, "2022-02-14", "CRM"),
                e(7, "Sara",   "HR",           62000, "2017-09-09", "Recruiting", "Payroll"),
                e(8, "Vikram", "Finance",      80000, "2015-04-30", "Excel", "SQL"));

        //Solution Q1

//        emps.stream()
//                 .filter(e -> e.dept.equals("Engineering"))
//                 .sorted(Comparator.comparing((Employee e) -> e.salary).reversed())
//                 .forEach(e -> System.out.println(e.name));
//
//        emps.stream()
//                .filter(e -> e.dept.equals("Engineering"))
//                .sorted((e1, e2) -> e2.salary.compareTo(e1.salary))
//                .forEach(e -> System.out.println(e.name));

        List<String> ls = emps.stream()
                .filter(e -> e.dept().equals("Engineering"))
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .map(Employee::name)
                .toList();
        System.out.println(ls);

        //Solution Q2

        List<String> ls2 = emps.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed()).limit(2)
                .map(Employee::name)
                .toList();
        System.out.println(ls2);

        //Solution Q3

        List<Employee> ls3 = emps.stream()
                .filter(e -> e.hireDate().isBefore(LocalDate.of(2018, 1, 1)))
                .sorted(Comparator.comparing(Employee::hireDate))
                .toList();
        for(Employee e : ls3){
            System.out.print(e.name+"(" + e.hireDate.getYear()+ ")");
        }
        System.out.println();

        //Solution Q4

        String joinedNames = emps.stream()
                .map(Employee::name)
                .collect(Collectors.joining(", "));

        System.out.println(joinedNames);

        //Solution Q5

//        int tot = emps.stream()
//                .mapToInt(e -> e.salary().intValue())
//                .sum();

        int tot = emps.stream()
                .mapToInt(e -> e.salary().intValue())
                .reduce(0, Integer::sum);
        System.out.println(tot);

    }

}
