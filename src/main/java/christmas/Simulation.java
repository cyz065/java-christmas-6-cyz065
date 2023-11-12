package christmas;

import event.Discount;
import java.util.Map;
import menu.Menu;
import order.Order;
import view.InputView;
import view.OutputView;

public class Simulation {
    private Order order;
    private Discount discount;
    private int day;

    public Simulation() {
        Menu menu = new Menu();
    }

    public void run() {
        try {
            reservation();
            showResult();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void reservation() {
        day = InputView.inputDay();
        Map<String, Integer> orderHistory = InputView.inputOrder();
        order = new Order(orderHistory);
        discount = new Discount(order.isEventTarget(), day, order);
    }

    private void showResult() {
        OutputView.printPreview(day);

        order.showOrderHistory();
        order.showTotalPriceBeforeDiscount();
        order.showGift();

        discount.showBenefitHistory();
        discount.showTotalBenefit();
        discount.showExpectedPrice();
        discount.showEventBadge();
    }



}
