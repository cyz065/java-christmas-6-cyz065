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
        int discount = 1000;
        if (isEventTarget && day <= 25) {
            for (int date = 1; date < day; date++) {
                discount += 100;
            }

            return discount;
        }

        return 0;
    }

    public int weekDayDiscount(Order order) {
        int count = 0;
        boolean isWeekend = isWeekEnd();
        boolean containsDessert = order.isContainsDessert();

        if (!isWeekend && containsDessert) {
            count = order.getItemCount(Constant.DESSERT);
        }
        return count * Constant.DAY_DISCOUNT;
    }

    public int weekEndDiscount() {
        return 0;
    }

    public int specialDiscount() {
        return 0;
    }

    private boolean isWeekEnd() {
        int dayOfMonth = day % 7;
        Day day = Day.getDay(dayOfMonth);
        if(day == Day.FRIDAY || day == Day.SATURDAY) {
            return true;
        }
        return false;
    }
}
