package order;

import constant.Constant;
import java.util.Map;
import menu.Food;
import menu.Menu;
import view.OutputView;

public class Order {
    private final Map<String, Integer> order;
    private int total;

    public Order(Map<String, Integer> order) {
        this.order = order;
        total = getTotalPriceBeforeDisCount();
    }

    public int getTotalPriceBeforeDisCount() {
        total = 0;
        for (String item : order.keySet()) {
            total += Food.getPriceByName(item) * order.get(item);
        }

        return total;
    }

    public boolean isEventTarget() {
        if (total >= Constant.TARGET_PRICE) {
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
        if (total >= Constant.GIFT_TARGET_PRICE) {
            return true;
        }
        return false;
    }

    public void showOrderHistory() {
        OutputView.printMenu(order);
    }

    public void showTotalPriceBeforeDiscount() {
        OutputView.printTotalPriceBeforeDiscount(total);
    }

    public void showGift() {
        if (checkGiftTarget()) {
            OutputView.printGift(Constant.GIFT);
            return;
        }

        OutputView.printGift(Constant.NOTHING);
    }
}
