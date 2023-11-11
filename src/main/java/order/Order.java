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

    public boolean hasCategoryItem(String category) {
        for (String item : order.keySet()) {
            if (Menu.getItemCategory(item).equals(category)) {
                return true;
            }
        }
        return false;
    }

    public int getItemCount(String category) {
        int count = 0;
        for (String item : order.keySet()) {
            if (Menu.getItemCategory(item).equals(category)) {
                count += order.get(item);
            }
        }

        return count;
    }

    public boolean checkGiftTarget() {
        if (getTotalPriceBeforeDisCount() >= Constant.GIFT_TARGET_PRICE) {
            int champagneCount = order.getOrDefault(Food.CHAMPAGNE.getName(), 0);
            order.put(Food.CHAMPAGNE.getName(), champagneCount + 1);
            return true;
        }
        return false;
    }

}
