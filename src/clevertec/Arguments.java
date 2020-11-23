package clevertec;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Arguments {
    private String pathFileProductInput;
    private String pathFileCheckOutput;
    private String pathFileCardInput;
    private Map<Integer, Integer> order;
    private String discountCard;

    {
        pathFileProductInput = Constants.DEFAULT_PATH_FILE_PRODUCT_INPUT;
        pathFileCheckOutput = Constants.DEFAULT_PATH_FILE_CHECK_OUTPUT;
        pathFileCardInput = Constants.DEFAULT_PATH_FILE_CARD_INPUT;
        order = new HashMap<>();
        discountCard = "";
    }

    public void parseArguments(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-products":
                    pathFileProductInput = args[++i];
                    break;
                case "-check":
                    pathFileCheckOutput = args[++i];
                    break;
                case "-cards":
                    pathFileCardInput = args[++i];
                    break;
                case "-buy":
                    order = readOrder(args[++i]);
                    break;
                case "-dsc":
                    discountCard = args[++i];
                    break;
                default:
                    System.out.println("Unknown argument: " + args[i]);
                    break;
            }
        }
    }

    private Map<Integer, Integer> readOrder(String str) {
        Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            order.put(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        }
        return order;
    }

    public String getPathFileProductInput() {
        return pathFileProductInput;
    }

    public String getPathFileCheckOutput() {
        return pathFileCheckOutput;
    }

    public String getPathFileCardInput() {
        return pathFileCardInput;
    }

    public Map<Integer, Integer> getOrder() {
        return order;
    }

    public String getDiscountCard() {
        return discountCard;
    }
}
