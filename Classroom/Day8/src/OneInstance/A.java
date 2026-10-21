//Singelton Design Pattern
package OneInstance;
public class A {
    private A() {
        System.out.println("Inside Constructor");
    }
    private static final A obj = new A();
    public static A createInstance() {
        return obj;
    }
}
