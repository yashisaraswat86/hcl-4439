package Streams;

import java.util.ArrayList;
import java.util.stream.Gatherer;

public class StreamImp {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>() ;
        ArrayList<String> list1 = new ArrayList<>() ;
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list1.add("Mango");
        list1.add("Banana");
        list1.add("Grapes");
        list1.add("Leeche");
        System.out.println(list.stream().filter(x -> x % 2 == 0).count()) ;
        System.out.println(list1.stream().findFirst());
        list1.stream().forEach(x -> System.out.println(x));


    }
}
