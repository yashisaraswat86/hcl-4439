package FunInterface;

public class Calculations {
    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;
        Calculator sub = (a, b) -> a - b;
        Calculator div = (a, b) -> a / b;
        Calculator mod = (a, b) -> a % b;
        Calculator mul = (a,b) -> a * b ;

        System.out.println(add.calculate(10, 5));
        System.out.println(sub.calculate(10, 5));
        System.out.println(div.calculate(10, 5));
        System.out.println(mod.calculate(10, 5));
        System.out.println(mul.calculate(10, 5));
    }
}
