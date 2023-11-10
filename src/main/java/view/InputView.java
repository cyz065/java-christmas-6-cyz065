package view;

import camp.nextstep.edu.missionutils.Console;
import constant.Constant;
import constant.Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Map<String, Integer> setOrder(String input) {
        Map<String, Integer> menu = new HashMap<>();
        String[] menuPair = input.split(",");

        for (String pair : menuPair) {
            try {
                String[] item = pair.split("-");
                checkInputOrder(item);

                menu.put(item[0], Integer.parseInt(item[1]));
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }

        return menu;
    }

    private static void checkInputOrder(String[] pair) throws IllegalArgumentException {
        try {
            checkLength(pair);
            checkItem(pair[0]);
            checkCount(pair[1]);

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkLength(String[] pair) throws IllegalArgumentException {
        if (pair.length != 2) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static void checkItem(String item) throws IllegalArgumentException {
        if (!Menu.containsItem(item)) {
            throw new IllegalArgumentException(Exception.MENU_ERROR);
        }
    }

    private static int checkCount(String input) throws IllegalArgumentException {
        int count = checkValid(input);
        checkRange(count);

        return count;
    }

    private static int checkValid(String input) throws IllegalArgumentException {
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
