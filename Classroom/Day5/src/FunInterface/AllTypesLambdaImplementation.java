package FunInterface;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;
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
    }
}
