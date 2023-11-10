package view;

import camp.nextstep.edu.missionutils.Console;
import constant.Exception;
import java.io.IOException;

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

    public static int setDay(String input) {
        try {
            int day = Integer.parseInt(input);
            checkDay(day);

            return day;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Exception.DAY_FORMAT_ERROR);
        }
    }

    public static void checkDay(int day) throws IllegalArgumentException {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(Exception.DAY_RANGE_ERROR);
        }
    }



}
