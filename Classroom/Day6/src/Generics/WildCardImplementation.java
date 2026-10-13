package Generics;

import java.util.Arrays;
import java.util.List;

public class WildCardImplementation {
    static void display(List<?> list) {
        for (Object x : list) {
            System.out.println(x);
        }
    }
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(10, 20, 30);
        List<String> l2 = Arrays.asList("Java", "Python");
        List<Double> l3 = List.of(1.2 , 1.3 ,1.4) ;

        display(l1);
        display(l2);
        display(l3);
    }
}
