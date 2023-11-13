package event;

import constant.Constant;
import java.util.HashMap;
import java.util.Map;
import menu.Food;
import order.Order;

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

    public Map<String, Integer> makeBenefitHistory() {
        Map<String, Integer> discountHistory = setDiscountHistory();
        discountHistory.values().removeIf(price -> price == 0);

        if (order.checkGiftTarget()) {
            discountHistory.put(Constant.GIFT_EVENT, Food.CHAMPAGNE.getPrice() * (-1));
        }

        return discountHistory;
    }

    public Map<String, Integer> setDiscountHistory() {
        Map<String, Integer> discountHistory = new HashMap<>();
        discountHistory.put(Constant.CHRISTMAS_D_DAY, christMasDiscount() * (-1));
        discountHistory.put(Constant.WEEKEND, weekEndDiscount() * (-1));
        discountHistory.put(Constant.WEEKDAY, weekDayDiscount() * (-1));
        discountHistory.put(Constant.SPECIAL, specialDiscount() * (-1));

        return discountHistory;
    }
}
