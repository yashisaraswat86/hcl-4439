package Switch;

import java.util.Scanner;

public class DaysUsingSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a day name : ");
        String dayName = scanner.next();

        String dayType = switch (dayName) {

            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";

            case "Saturday", "Sunday" -> {
                yield "Weekend";
            }
            default -> "Invalid day entered!";
        };

        System.out.println("Type of day: " + dayType);

    }
}
