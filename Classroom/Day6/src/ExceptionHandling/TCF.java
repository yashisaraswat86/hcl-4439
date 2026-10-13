package ExceptionHandling;

public class TCF {
    public static void main(String[] args){
        int a = 10 ;
        int b = 5 ;
        try{
            int div = a / 0 ;
            return ;
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.exit(8);// finally block does not excute in this case , otherwise always executes
            System.out.println("Program Completed");

        }
    }
}
