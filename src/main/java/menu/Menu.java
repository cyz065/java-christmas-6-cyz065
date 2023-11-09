package menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private Map<String, List<String>> menu;

    public Menu() {
        menu = setMenu();
    }

    public static Map<String, List<String>> setMenu() {
        Map<String, List<String>> menu = new HashMap<>();

        for (Food food : Food.values()) {
            menu.put(food.getCategory(), Food.getFoodsByCategory(food.getCategory()));
        }

        return menu;
    }
}
