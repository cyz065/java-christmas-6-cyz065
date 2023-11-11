package event;

import constant.Constant;
import menu.Menu;
import order.Order;

public class Discount {
    private boolean isEventTarget;
    private int day;

    public Discount(boolean isEventTarget, int day) {
        this.isEventTarget = isEventTarget;
        this.day = day;
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

    public int weekDayDiscount(Order order) {
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

    public int weekEndDiscount(Order order) {
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
        if (isEventTarget) {
            boolean isSpecialDay = isSpecialDay();

            if (isSpecialDay) {
                return Constant.SPECIAL_DISCOUNT;
            }
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

    public int getTotalDiscount(Order order) {
        int total = 0;
        total += christMasDiscount();
        total += weekDayDiscount(order);
        total += weekEndDiscount(order);
        total += specialDiscount();

        return total;
    }
}
