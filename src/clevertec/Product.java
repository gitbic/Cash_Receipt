package clevertec;

import java.math.BigDecimal;
import java.util.Scanner;

public final class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private boolean discountForQuantity;

    public Product(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(value);
        discountForQuantity = value >= Constants.PRICE_FOR_DISCOUNT;
    }

    public Product(Scanner scanner) {
        this(scanner.nextInt(), scanner.next(), scanner.nextDouble());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isDiscountForQuantity() {
        return discountForQuantity;
    }

    public void setDiscountForQuantity(boolean discountForQuantity) {
        this.discountForQuantity = discountForQuantity;
    }


    public String toCheck() {
        int num = 15 - name.length();
        String space = String.format("%" + num + "s", "");
        return name + space +
                Utility.priceToString(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", price=" + Utility.priceToString(price) +
                (discountForQuantity ? ", discount for quantity" : "") +
                '}';
    }
}
