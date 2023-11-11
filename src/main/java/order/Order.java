package order;

import constant.Constant;
import java.util.Map;
import menu.Food;
import menu.Menu;

public class Order {
    private Map<String, Integer> order;

    public Order(Map<String, Integer> order) {
        this.order = order;
    }

    public int getTotalPriceBeforeDisCount() {
        int total = 0;
        for (String item : order.keySet()) {
            total += Food.getPriceByName(item) * order.get(item);
        }

        return total;
    }

    public boolean isEventTarget() {
        if (getTotalPriceBeforeDisCount() >= Constant.TARGET_PRICE) {
            return true;
        }
        return false;
    }

    public boolean isContainsDessert() {
        for (String item : order.keySet()) {
            if (Menu.getItemCategory(item).equals(Constant.DESSERT)) {
                return true;
            }
        }
        return false;
    }

    public int getItemCount(String category) {
        int count = 0;
        for (String item : order.keySet()) {
            if (Menu.getItemCategory(item).equals(category)) {
                count += 1;
            }
        }

        return count;
    }

}
