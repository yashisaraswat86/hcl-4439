package ExceptionHandling;

public class throwsimplementation {

//    int throwsimplementation(int a , int b) throws  ArithmeticException{
//        int d = a/b ;
//        return d ;
//    }
    int threadimp(int a , int b) throws InterruptedException{
        Thread.sleep(100);
        return a  ;
    }
    public static void main(String[] args) throws InterruptedException{
        throwsimplementation t = new throwsimplementation();
        //t.throwsimplementation(10,5);
        System.out.println(t.threadimp(3,6));
    }
}
