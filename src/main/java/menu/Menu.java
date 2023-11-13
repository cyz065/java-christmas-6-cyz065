package menu;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private static Map<String, String> itemWithCategory;
    public Menu() {
        itemWithCategory = setMenuByItem();
    }

    // 음식 별 카테고리 나열 ex) 양송이수프 - 애피타이저, 티본스테이크 - 메인
    private Map<String, String> setMenuByItem() {
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
