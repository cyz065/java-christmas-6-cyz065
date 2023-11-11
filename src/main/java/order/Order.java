package order;

import java.util.Map;
import menu.Food;

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

    public boolean isEventTarget(int total) {
        if (total >= 10_000) {
            return true;
        }
        return false;
    }

}
