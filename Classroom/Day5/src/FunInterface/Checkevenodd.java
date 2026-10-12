package FunInterface;

public class Checkevenodd {
    public static void main(String[] args){
        Evenodd printevenodd = (int x) -> {
            if( x % 2 == 0){
                return true ;
            }else{
                return false ;
            }
        };
        System.out.println(printevenodd.check(4));
    }
}
