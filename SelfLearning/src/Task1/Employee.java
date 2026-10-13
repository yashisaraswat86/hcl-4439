package Task1;

import java.util.*;
import java.util.Iterator;
import java.util.List;

public class Employee {
    int id ;
    int age ;
    String name ;
    String dept ;

    public Employee(int id , String name , int age , String dept) {
        this.id = id;
        this.name = name ;
        this.age = age ;
        this.dept = dept ;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' + "age=" + age +
                ", dept='" + dept + '\'' +
                '}';
    }

    public static void main(String[] args){
        List<Employee> list = new ArrayList<>() ;
        list.add(new Employee(102 , "Raj" , 50 , "IT"));
        list.add(new Employee(101 , "Rahul" , 40 , "Finance"));
        list.add(new Employee(103 , "Smriti" , 40 , "Finance"));

        System.out.println(list);

        Collections.sort(list  , Comparator.comparingInt((Employee e) -> e.id).reversed());
        Collections.sort(list , Comparator.comparingInt((Employee e) -> e.age));
        Collections.sort(list , Comparator.comparing((Employee e) -> e.name).reversed());

        Iterator<Employee> it = list.iterator() ;
        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
