package menu;

import java.util.ArrayList;
import java.util.List;

public enum Food {
    MUSHROOM_SOUP("애피타이저", "양송이수프", 6_000), TAPAS("애피타이저", "타파스", 5_500), CAESAR_SALAD("애피타이저","시저샐러드", 8_000),
    T_BONE_STEAK("메인","티본스테이크", 55_000), BARBEQUE_RIB("메인","바비큐립", 54_000), SEA_FOOD_PASTA("메인","해산물파스타", 35_000), CHRISTMAS_PASTA("메인","크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("디저트","초코케이크", 15_000), ICE_CREAM("디저트","아이스크림", 5_000),
    ZERO_COKE("음료","제로콜라", 3_000), RED_WINE("음료","레드와인", 60_000), CHAMPAGNE("음료","샴페인", 25_000);

    private String category;
    private String name;
    private int price;
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
