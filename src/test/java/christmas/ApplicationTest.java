package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import constant.Exception;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import menu.Food;
import menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-","레드와인- ","초코케이크--1"})
    void 주문_입력_검증_테스트(String item) {
        assertThatThrownBy(() -> InputView.setOrder(item))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(Exception.MENU_ERROR);
    }
    @ParameterizedTest
    @ValueSource(strings = {"-1", "32", "test", "1a"})
    void 날짜_입력_에러_테스트(String day) {
        assertThatThrownBy(() -> InputView.setDay(day))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @CsvSource(value = {"애피타이저:타파스,양송이수프,시저샐러드", "메인:해산물파스타,티본스테이크,크리스마스파스타,바비큐립"
    ,"디저트:아이스크림,초코케이크", "음료:제로콜라,레드와인,샴페인"}, delimiter = ':')
    @DisplayName("카테고리별로 메뉴가 생성되는지 확인한다.")
    void menu(String category, String foods) {
        String[] data = foods.split(",");
        List<String> foodItems = new ArrayList<>(Arrays.asList(data));

        assertThat(foodItems.containsAll(Food.getFoodsByCategory(category))).isTrue();
    }

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
