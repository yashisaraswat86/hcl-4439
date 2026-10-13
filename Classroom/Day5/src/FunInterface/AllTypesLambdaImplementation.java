package FunInterface;
import java.util.function.*;

public class AllTypesLambdaImplementation {
    public static void main(String[] args) {

        // 1. No parameter, no return value
        Runnable r = () -> System.out.println("Hello Java");
        r.run();

        // 2. Parameter, no return value
        Consumer<Integer> c = x -> System.out.println("Number: " + x);
        c.accept(10);

        // 3. No parameter, return value
        Supplier<Integer> s = () -> 100;
        System.out.println("Value: " + s.get());

        // 4. Parameter, return value
        Predicate<Integer> p = x -> x % 2 == 0;
        System.out.println("Even: " + p.test(4));
        System.out.println("Even: " + p.test(7));

        //Function
        BiFunction<Integer , Integer , Integer> f = (a,b) -> a + b ;
        System.out.println(f.apply(7,8));

        Function<Integer , Integer> sq = (x) -> x*x ;
        System.out.println(sq.apply(4));

    }
}
