package view;


import constant.Constant;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    static DecimalFormat moneyFormat = new DecimalFormat("###,###");

    public static void printTitle() {
        System.out.println(Constant.TITLE);
    }

    public static void printPreview(int day) {
        System.out.println(Constant.EVENT_MONTH + "월 " + day + Constant.EVENT_PREVIEW);
        System.out.println();
    }

    public static void printMenu(Map<String, Integer> order) {
        System.out.println(Constant.PRINT_ORDER);
        for (String item : order.keySet()) {
            System.out.println(item + " " + order.get(item) + "개");
        }
        System.out.println();
    }

    public static void printTotalPriceBeforeDiscount(int price) {
        System.out.println(Constant.PRINT_TOTAL_BEFORE_DISCOUNT);
        System.out.println(moneyFormat.format(price) + "원");
        System.out.println();
    }

    public static void printGift(String gift) {
        System.out.println(Constant.GIFT_MENU);
        System.out.println(gift);
        System.out.println();
    }

    public static void printBenefitHistory(Map<String, Integer> discount) {
        System.out.println(Constant.BENEFIT_HISTORY);
        int count = 0;
        for (String item : discount.keySet()) {
            if (discount.get(item) != 0) {
                System.out.println(item + ":" + moneyFormat.format(discount.get(item)) + "원");
                count += 1;
            }
        }

        if (count == 0) {
            System.out.println(Constant.NOTHING);
        }
        System.out.println();
    }

    public static void printTotalBenefit(int amount) {
        System.out.println(Constant.TOTAL_BENEFIT);
        System.out.println(moneyFormat.format(amount) + "원");
        System.out.println();
    }

    public static void printExpectedPrice(int amount) {
        System.out.println(Constant.EXPECTED_PRICE);
        System.out.println(moneyFormat.format(amount) + "원");
        System.out.println();
    }

    public static void printEventBadge(String badge) {
        System.out.println(Constant.EVENT_BADGE);
        System.out.print(badge);
    }
}
