package Task1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employee {
    private int id ;
    String name ;
    String dept ;

    public Employee(int id , String name , String dept) {
        this.id = id;
        this.name = name ;
        this.dept = dept ;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                '}';
    }

    public static void main(String[] args){
        List<Employee> list = new ArrayList<>() ;
        list.add(new Employee(101 , "Raj" , "IT"));
        list.add(new Employee(102 , "Rahul" , "Finance"));

        System.out.println(list);
        Iterator<Employee> it = list.iterator() ;
        while(it.hasNext()){
            System.out.println(it.next());
        }


    }
}
