package clevertec;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class MainOrder {
    private final List<Purchase> purchases;
    private final DiscountCard discountCard;

    public MainOrder(DiscountCard discountCard) {
        this.purchases = new ArrayList<>();
        this.discountCard = discountCard;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public Purchase getPurchaseFromList(int i) {
        return purchases.get(i);
    }

    public void removePurchaseFromList(int i) {
        purchases.remove(i);
    }

    public void addPurchaseToList(Purchase purchase) {
        purchases.add(purchase);
    }

    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }

    public BigDecimal getDiscountCost() {
        BigDecimal discount = BigDecimal.ZERO;
        if (discountCard != null) {
            discount = getTotalCost().multiply(BigDecimal.valueOf(discountCard.getDiscount() / 100));
        }
        return discount;
    }

    public BigDecimal getFinalCost() {
        return getTotalCost().subtract(getDiscountCost());
    }

    public String getCheck() {
        StringBuilder sb = new StringBuilder();
        String tab = Constants.TAB_SPACE;
        String tabNum = tab.repeat(6);
        String separator = "=".repeat(42) + System.lineSeparator();

        String head = "QTY" + tab
                + "DESCRIPTION" + tab + tab
                + "PRICE" + tab
                + "TOTAL" + tab
                + "DISC" + System.lineSeparator()
                + separator;

        StringBuilder body = new StringBuilder();
        for (Purchase purchase : purchases) {
            body.append(purchase.toCheck()).append(System.lineSeparator());
        }

        String tail = separator
                + "TOTAL" + tabNum + Utility.priceToString(getTotalCost()) + System.lineSeparator()
                + "DISC" + tabNum + Utility.priceToString(getDiscountCost())
                + tab + Utility.percentToString(discountCard.getDiscount()) + System.lineSeparator()
                + "TO PAY" + tabNum + Utility.priceToString(getFinalCost()) + System.lineSeparator();

        return head + body + tail;
    }
}
