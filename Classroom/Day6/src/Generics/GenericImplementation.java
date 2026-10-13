package Generics;

class Box<T extends Number, E extends Number> {
    T a;
    E b;

    Box(T a, E b) {
        this.a = a;
        this.b = b;
    }

    void display() {
        System.out.println(a);
        System.out.println(b);
    }

    double sum(T a, E b) {
        return a.doubleValue() + b.doubleValue();
    }
}

public class GenericImplementation {
    public static void main(String[] args) {

        Box<Integer, Double> b1 = new Box<>(100, 10.5);
        b1.display();

        System.out.println(b1.sum(10, 20.0));
    }
}
