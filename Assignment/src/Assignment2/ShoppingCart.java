package Assignment2;

import java.util.*;

public class ShoppingCart {
    private final Map<Integer, Product> products = new HashMap<>();

    public synchronized void addProduct(Product product) {
        if (product == null) {
            System.out.println("Product cannot be null.");
            return;
        }

        if (products.containsKey(product.getId())) {
            System.out.println("Product ID already exists in cart.");
            return;
        }

        products.put(product.getId(), product);
        System.out.println("Product added successfully.");
    }

    public synchronized void removeProduct(int id) {
        if (products.remove(id) != null) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public synchronized void updateProduct(
            int id, String name, double price) {
        Product product = products.get(id);

        if (product == null) {
            System.out.println("Product not found in cart.");
            return;
        }

        try {
            product.update(name, price);
            System.out.println("Product updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void showCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        List<Product> sortedProducts =
                new ArrayList<>(products.values());

        sortedProducts.sort(
                Comparator.comparing(
                        Product::getName,
                        String.CASE_INSENSITIVE_ORDER
                ).thenComparingInt(Product::getId)
        );

        System.out.println("\n--------- SHOPPING CART ---------");

        double total = 0;

        for (Product product : sortedProducts) {
            product.display();
            total += product.getPrice();
        }

        System.out.printf("Total: Rs. %.2f%n", total);
        System.out.println("---------------------------------");
    }
}

