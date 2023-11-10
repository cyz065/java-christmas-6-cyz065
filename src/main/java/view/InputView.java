package view;

import camp.nextstep.edu.missionutils.Console;

import constant.Constant;
import constant.Exception;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import menu.Menu;

public class InputView {
    public static int inputDay() {
        while (true) {
            try {
                return setDay(Console.readLine());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static Map<String, Integer> inputOrder() {
        while (true) {
            try {
                return setOrder(Console.readLine());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static int setDay(String input) {
        try {
            int day = Integer.parseInt(input);
            checkDay(day);
            return day;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Exception.DAY_FORMAT_ERROR);
        }
    }

    private static void checkDay(int day) throws IllegalArgumentException {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(Exception.DAY_RANGE_ERROR);
        }
    }

    public static Map<String, Integer> setOrder(String input) throws IllegalArgumentException {
        // order: {아이템, 수량} ex) {레드와인 = 1}
        Map<String, Integer> order = new HashMap<>();
        String[] orderPair = input.split(",");
        try {
            fillOrder(orderPair, order);
            checkOnlyDrink(order);
            checkTotalCount(order);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        return order;
    }

    private static void fillOrder(String[] orderPair, Map<String, Integer> order) throws IllegalArgumentException {
        for (String pair : orderPair) {
            String[] item = pair.split("-");
            try {
                checkInputOrder(item, order);
                order.put(item[0], Integer.parseInt(item[1]));
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(Exception.MENU_ERROR);
            }
        }
    }

    private static void checkInputOrder(String[] pair, Map<String, Integer> order) throws IllegalArgumentException {
        try {
            checkLength(pair);
            checkMenuContainsItem(pair[0]);
            checkItemDuplication(pair[0], order);
            checkValidCount(pair[1]);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkOnlyDrink(Map<String, Integer> order) throws IllegalArgumentException {
        Set<String> category = new HashSet<>();
        for (String item : order.keySet()) {
            category.add(Menu.getItemCategory(item));
        }

        if (category.size() == 1 && category.contains(Constant.DRINK)) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkTotalCount(Map<String, Integer> order) {
        int total = 0;
        for (String item : order.keySet()) {
            total += order.get(item);
        }

        if (total > 20) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkLength(String[] pair) throws IllegalArgumentException {
        if (pair.length != 2) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkMenuContainsItem(String item) throws IllegalArgumentException {
        if (!Menu.containsItem(item)) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkItemDuplication(String item, Map<String, Integer> order) throws IllegalArgumentException {
        if (order.getOrDefault(item, null) != null) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkValidCount(String input) throws IllegalArgumentException {
        int count = convertToInt(input);
        checkRange(count);
    }

    private static int convertToInt(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkRange(int count) throws IllegalArgumentException {
        if (count <= 0) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }
}