import clevertec.*;

import java.util.*;

public class CheckRunner {
    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        Arguments arguments = new Arguments();
        arguments.parseArguments(args);

        Factory factory = new Factory();

        // create map products: key(id), value(Product)
        String[] lines = fileIO
                .read(arguments.getPathFileProductInput())
                .split(System.lineSeparator());

        Map<Integer, Product> productMap = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            productMap.put(Integer.parseInt(elements[0]), new Product(
                    Integer.parseInt(elements[0]),
                    elements[1],
                    Double.parseDouble(elements[2])));
        }

        // create map of discount card: key(number), value(DiscountCard);
        lines = fileIO
                .read(arguments.getPathFileCardInput())
                .split(System.lineSeparator());

        Map<String, DiscountCard> discountCardMap = new HashMap<>();
        for (String line : lines) {
            String[] elements = line.split(Constants.CSV_DELIMITER);
            discountCardMap.put(elements[0], new DiscountCard(
                    elements[0],
                    Double.parseDouble(elements[1])));
        }

        // create main order for all purchases
        MainOrder mainOrder = new MainOrder(discountCardMap.get(arguments.getDiscountCard()));
        for (Map.Entry<Integer, Integer> position : arguments.getOrder().entrySet()) {
            Product product = productMap.get(position.getKey());
            mainOrder.addPurchaseToList((factory.createPurchase(product, position.getValue())));
        }

        System.out.println(mainOrder.getCheck());


    }


}


