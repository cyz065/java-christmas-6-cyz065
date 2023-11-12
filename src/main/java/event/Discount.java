package event;

import constant.Constant;
import java.util.HashMap;
import java.util.Map;
import menu.Food;
import menu.Menu;
import order.Order;
import view.OutputView;

public class Discount {
    private final boolean isEventTarget;
    private final int day;
    private final Order order;

    public Discount(boolean isEventTarget, int day, Order order) {
        this.isEventTarget = isEventTarget;
        this.day = day;
        this.order = order;
    }

    public int christMasDiscount() {
        int discount = Constant.SPECIAL_DISCOUNT;
        if (isEventTarget && day <= Constant.CHRISTMAS) {
            for (int date = 1; date < day; date++) {
                discount += Constant.CHRISTMAS_DISCOUNT;
            }

            return discount;
        }

        return Constant.NO_DISCOUNT;
    }

    public int weekDayDiscount() {
        int count = 0;
        if(isEventTarget) {
            boolean isWeekend = isWeekEnd();
            boolean containsDessert = order.hasCategoryItem(Constant.DESSERT);

            if (!isWeekend && containsDessert) {
                count = order.getItemCount(Constant.DESSERT);
            }
        }
        return count * Constant.DAY_DISCOUNT;
    }

    public int weekEndDiscount() {
        int count = 0;
        if (isEventTarget) {
            boolean isWeekend = isWeekEnd();
            boolean containsMain = order.hasCategoryItem(Constant.MAIN);

            if (isWeekend && containsMain) {
                count = order.getItemCount(Constant.MAIN);
            }
        }

        return count * Constant.DAY_DISCOUNT;
    }

    public int specialDiscount() {
        if (isEventTarget && isSpecialDay()) {
            return Constant.SPECIAL_DISCOUNT;
        }

        return Constant.NO_DISCOUNT;
    }

    private boolean isWeekEnd() {
        int dayOfMonth = day % 7;
        Day date = Day.getDay(dayOfMonth);

        if (date == Day.FRIDAY || date == Day.SATURDAY) {
            return true;
        }

        return false;
    }

    private boolean isSpecialDay() {
        int dayOfMonth = day % 7;
        Day date = Day.getDay(dayOfMonth);

        if (date == Day.SUNDAY || day == Constant.CHRISTMAS) {
            return true;
        }

        return false;
    }

    public int getTotalDiscount() {
        int total = 0;

        total += christMasDiscount();
        total += weekDayDiscount();
        total += weekEndDiscount();
        total += specialDiscount();

        return total;
    }

    public void showBenefitHistory() {
        Map<String, Integer> discount = new HashMap<>();

        discount.put(Constant.CHRISTMAS_D_DAY, christMasDiscount() * (-1));
        discount.put(Constant.WEEKEND, weekEndDiscount() * (-1));
        discount.put(Constant.WEEKDAY, weekDayDiscount() * (-1));
        discount.put(Constant.SPECIAL, specialDiscount() * (-1));

        if (order.checkGiftTarget()) {
            discount.put(Constant.GIFT_EVENT, Food.CHAMPAGNE.getPrice() * (-1));
        }
        OutputView.printBenefitHistory(discount);
    }

    public void showTotalBenefit() {
        int amount = getTotalDiscount();
        if (order.checkGiftTarget()) {
            amount += Food.CHAMPAGNE.getPrice();
        }
        OutputView.printTotalBenefit(amount * (-1));
    }

    public void showExpectedPrice() {
        OutputView.printExpectedPrice(order.getTotalPriceBeforeDisCount() - getTotalDiscount());
    }

    public void showEventBadge() {
        int total = getTotalDiscount();

        if (order.checkGiftTarget()) {
            total += 25000;
        }

        for (Badge badge : Badge.values()) {
            if (total >= badge.getAmount()) {
                OutputView.printEventBadge(badge.getBadgeName());
                break;
            }
        }
    }
}
