package Assignment2;

public class User {
    private final int id;
    private final String name;
    private final ShoppingCart cart;

    public User(int id, String name) {
        if (id <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid user details");
        }

        this.id = id;
        this.name = name;
        this.cart = new ShoppingCart();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}

