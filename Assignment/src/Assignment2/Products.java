package Assignment2;

import java.util.*;

public class Products {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, User> users = new LinkedHashMap<>();
    private static int nextProductId = 1;

    public static void main(String[] args) {
        users.put(102, new User(102, "Ram"));
        users.put(101, new User(101, "Rahul"));
        users.put(103, new User(103, "Diya"));

        while (true) {
            System.out.println("\n===== SHOPPING APPLICATION =====");
            System.out.println("1. Select User");
            System.out.println("2. Exit");

            int choice = readInt("Enter choice: ");

            if (choice == 2) {
                System.out.println("Thank you!");
                break;
            }

            if (choice != 1) {
                System.out.println("Invalid choice.");
                continue;
            }

            showUsers();

            int userId = readInt("Enter user ID: ");
            User user = users.get(userId);

            if (user == null) {
                System.out.println("User not found.");
                continue;
            }

            userMenu(user);
        }

        sc.close();
    }

    private static void showUsers() {
        System.out.println("\nAvailable Users:");

        for (User user : users.values()) {
            System.out.println(user.getId() + " - " + user.getName());
        }
    }

    private static void userMenu(User user) {
        boolean back = false;

        while (!back) {
            System.out.println("\nWelcome, " + user.getName());
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Show Cart");
            System.out.println("5. Switch User");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1:
                        addProduct(user);
                        break;

                    case 2:
                        int removeId = readInt("Enter product ID: ");
                        user.getCart().removeProduct(removeId);
                        break;

                    case 3:
                        int updateId = readInt("Enter product ID: ");
                        String name = readText("Enter new name: ");
                        double price = readDouble("Enter new price: ");

                        user.getCart().updateProduct(
                                updateId, name, price
                        );
                        break;

                    case 4:
                        user.getCart().showCart();
                        break;

                    case 5:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addProduct(User user) {
        System.out.println("\n1. Clothes");
        System.out.println("2. Electronics");
        System.out.println("3. Toys");

        int category = readInt("Select category: ");

        if (category < 1 || category > 3) {
            System.out.println("Invalid category.");
            return;
        }

        String name = readText("Enter product name: ");
        double price = readDouble("Enter price: ");

        Product product;

        switch (category) {
            case 1:
                String size = readText("Enter size: ");
                product = new ClothesProduct(
                        nextProductId, name, price, size
                );
                break;

            case 2:
                int warranty = readInt("Enter warranty in months: ");
                product = new ElectronicProduct(
                        nextProductId, name, price, warranty
                );
                break;

            default:
                int age = readInt("Enter recommended age: ");
                product = new ToysProduct(
                        nextProductId, name, price, age
                );
                break;
        }

        user.getCart().addProduct(product);
        nextProductId++;
    }

    private static int readInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(readText(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(message));

                if (!Double.isFinite(value)) {
                    System.out.println("Enter a finite number.");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
}

