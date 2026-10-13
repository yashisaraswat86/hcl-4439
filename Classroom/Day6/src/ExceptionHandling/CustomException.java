
package ExceptionHandling;

class InvalidAgeException extends Exception {
    //Constructor to call parent class constructor
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomException {
    public static void main(String[] args) {
        int age = 16 ;
        try {
            if (age < 18) {
                throw new InvalidAgeException(
                        "Invalid age! You are not eligible to vote."
                );
            } else {
                System.out.println("You are eligible to vote.");
            }
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}

