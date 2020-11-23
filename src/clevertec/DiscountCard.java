package clevertec;

import java.util.Scanner;

public final class DiscountCard implements Comparable<DiscountCard>{
    private final String number;
    private final double discount;

    public DiscountCard(String number, double discount) {
        this.number = number;
        this.discount = discount;
    }

    public DiscountCard(Scanner scanner) {
        this(scanner.next(), scanner.nextDouble());
    }

    public String getNumber() {
        return number;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "number=" + number +
                ", discount=" + String.format("%.2f%%", discount) +
                '}';
    }

    @Override
    public int compareTo(DiscountCard o) {
        return this.number.compareTo(o.number);
    }
}
