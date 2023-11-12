package menu;

import constant.Constant;
import java.util.ArrayList;
import java.util.List;

public enum Food {
    MUSHROOM_SOUP(Constant.APPETIZER, "양송이수프", 6_000), TAPAS(Constant.APPETIZER, "타파스", 5_500), CAESAR_SALAD(Constant.APPETIZER,"시저샐러드", 8_000),
    T_BONE_STEAK(Constant.MAIN,"티본스테이크", 55_000), BARBEQUE_RIB(Constant.MAIN,"바비큐립", 54_000), SEA_FOOD_PASTA(Constant.MAIN,"해산물파스타", 35_000), CHRISTMAS_PASTA("메인","크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(Constant.DESSERT,"초코케이크", 15_000), ICE_CREAM(Constant.DESSERT,"아이스크림", 5_000),
    ZERO_COKE(Constant.DRINK,"제로콜라", 3_000), RED_WINE(Constant.DRINK,"레드와인", 60_000), CHAMPAGNE(Constant.DRINK,"샴페인", 25_000);

    private final String category;
    private final String name;
    private final int price;
    Food(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static List<String> getFoodsByCategory(String category) {
        List<String> foods = new ArrayList<>();

        for (Food food : values()) {
            if (food.category.equals(category)) {
                foods.add(food.name);
            }
        }

        return foods;
    }

    public static int getPriceByName(String name) {
        for (Food food : values()) {
            if (food.name.equals(name)) {
                return food.price;
            }
        }

        throw new IllegalArgumentException();
    }
}
