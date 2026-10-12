
package Assignment2;

public class Product {
    private final int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (id <= 0 || name == null || name.trim().isEmpty()
                || price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("Invalid product details");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void update(String name, double price) {
        if (name == null || name.trim().isEmpty()
                || price < 0 || !Double.isFinite(price)) {
            throw new IllegalArgumentException("Invalid product details");
        }

        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return "General";
    }

    public void display() {
        System.out.printf(
                "ID: %d | Name: %s | Category: %s | Price: Rs. %.2f%n",
                id, name, getCategory(), price
        );
    }
}
