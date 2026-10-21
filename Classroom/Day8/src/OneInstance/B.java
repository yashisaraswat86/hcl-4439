package OneInstance;
public class B {
    public static void main(String[] args) {
        A obj1 = A.createInstance();
        A obj2 = A.createInstance();
        A obj3 = A.createInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());

        System.out.println(obj1 == obj2);
        System.out.println(obj2 == obj3);
        System.out.println(obj3 == obj1);
    }
}
