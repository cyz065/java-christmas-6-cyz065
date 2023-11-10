package menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private static Map<String, List<String>> menu;
    private static Map<String, String> itemWithCategory;

    public Menu() {
        menu = setMenuByCategory();
        itemWithCategory = setMenuByItem();
    }

    public static Map<String, List<String>> setMenuByCategory() {
        Map<String, List<String>> menu = new HashMap<>();

        for (Food food : Food.values()) {
            menu.put(food.getCategory(), Food.getFoodsByCategory(food.getCategory()));
        }

        return menu;
    }

    public static Map<String, String> setMenuByItem() {
        Map<String, String> menu = new HashMap<>();

        for (Food food : Food.values()) {
            menu.put(food.getName(), food.getCategory());
        }

        return menu;
    }

    public static boolean containsItem(String item) {
        if (itemWithCategory.getOrDefault(item, null) != null) {
            return true;
        }
        return false;
    }

    public static String getItemCategory(String name) {
        return itemWithCategory.get(name);
    }
}
