package Assignment2;

public class ElectronicProduct extends Product {
    private final int warranty;

    public ElectronicProduct(int id, String name, double price, int warranty) {
        super(id, name, price);

        if (warranty < 0) {
            throw new IllegalArgumentException("Invalid warranty");
        }

        this.warranty = warranty;
    }

    @Override
    public String getCategory() {
        return "Electronics (" + warranty + " months warranty)";
    }
}

