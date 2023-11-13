package view;


import constant.Constant;
import event.Badge;
import event.Discount;
import java.text.DecimalFormat;
import java.util.Map;
import menu.Food;
import order.Order;

public class OutputView {
    static DecimalFormat moneyFormat = new DecimalFormat("###,###");

    public static void printTitle() {
        System.out.println(Constant.TITLE);
    }

    public static void printPreview(int day) {
        System.out.println(Constant.EVENT_MONTH + "월 " + day + Constant.EVENT_PREVIEW);
        System.out.println();
    }

    public static void printMenu(Order order) {
        System.out.println(Constant.PRINT_ORDER);

        Map<String, Integer> orderHistory = order.getOrderHistory();
        for (String item : orderHistory.keySet()) {
            System.out.println(item + " " + orderHistory.get(item) + "개");
        }
        System.out.println();
    }

    public static void printTotalPriceBeforeDiscount(Order order) {
        System.out.println(Constant.PRINT_TOTAL_BEFORE_DISCOUNT);
        System.out.println(moneyFormat.format(order.getTotalPriceBeforeDisCount()) + "원");
        System.out.println();
    }

    public static void printGift(Order order) {
        System.out.println(Constant.GIFT_MENU);

        if (order.checkGiftTarget()) {
            System.out.println(Constant.GIFT);
            System.out.println();
            return;
        }

        System.out.println(Constant.NOTHING);
        System.out.println();
    }

    public static void printBenefitHistory(Discount discount) {
        System.out.println(Constant.BENEFIT_HISTORY);
        Map<String, Integer> discountHistory = discount.makeBenefitHistory();

        if (discountHistory.size() == 0) {
            System.out.println(Constant.NOTHING);
            System.out.println();
            return;
        }
        for (String event : discountHistory.keySet()) {
            System.out.println(event + ":" + moneyFormat.format(discountHistory.get(event)) + "원");
        }

        System.out.println();
    }

    public static void printTotalBenefit(Order order, Discount discount) {
        System.out.println(Constant.TOTAL_BENEFIT);
        int amount = discount.getTotalDiscount();

        if (order.checkGiftTarget()) {
            amount += Food.CHAMPAGNE.getPrice();
        }

        System.out.println(moneyFormat.format(amount * (-1)) + "원");
        System.out.println();
    }

    public static void printExpectedPrice(Order order, Discount discount) {
        System.out.println(Constant.EXPECTED_PRICE);
        int amount = order.getTotalPriceBeforeDisCount() - discount.getTotalDiscount();

        System.out.println(moneyFormat.format(amount) + "원");
        System.out.println();
    }

    public static void printEventBadge(Order order, Discount discount) {
        System.out.println(Constant.EVENT_BADGE);
        int total = discount.getTotalDiscount();

        if (order.checkGiftTarget()) {
            total += 25000;
        }
        for (Badge badge : Badge.values()) {
            if (total >= badge.getAmount()) {
                System.out.println(badge.getBadgeName());
                break;
            }
        }
    }
}
