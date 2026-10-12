package Assignment2;

public class ToysProduct extends Product {
    private final int ageLimit;

    public ToysProduct(int id, String name, double price, int ageLimit) {
        super(id, name, price);

        if (ageLimit < 0) {
            throw new IllegalArgumentException("Invalid age limit");
        }

        this.ageLimit = ageLimit;
    }

    @Override
    public String getCategory() {
        return "Toys (Age " + ageLimit + "+)";
    }
}

