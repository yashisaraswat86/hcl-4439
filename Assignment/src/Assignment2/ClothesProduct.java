
package Assignment2;

public class ClothesProduct extends Product {
    private final String size;

    public ClothesProduct(int id, String name, double price, String size) {
        super(id, name, price);

        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid size");
        }

        this.size = size;
    }

    @Override
    public String getCategory() {
        return "Clothes (Size: " + size + ")";
    }
}

