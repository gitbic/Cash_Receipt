package clevertec;

import java.math.BigDecimal;

public class Purchase {
    private final Product product;
    private final int number;

    public Purchase(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getCost() {
        return product.getPrice().multiply(BigDecimal.valueOf(number));
    }

    public String toCheck() {
        return number + Constants.TAB_SPACE +
                product.toCheck() + Constants.TAB_SPACE +
                Utility.priceToString(getCost());
    }

    protected String fieldToString() {
        return "Purchase{" +
                product +
                ", number=" + number;
    }

    @Override
    public String toString() {
        return fieldToString() +
                ", cost=" + Utility.priceToString(getCost());
    }
}
