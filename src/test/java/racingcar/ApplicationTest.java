package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 우승자_1명인_경우_1명을_출력한다() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "5");

                assertThat(output()).contains(
                    "pobi : -", "woni : ", "jun : ",
                    "pobi : --", "woni : -", "jun : ",
                    "pobi : --", "woni : --", "jun : -",
                    "pobi : --", "woni : ---", "jun : --",
                    "pobi : --", "woni : ----", "jun : ---",
                    "최종 우승자 : woni");
            },
            MOVING_FORWARD, STOP, STOP,
            MOVING_FORWARD, MOVING_FORWARD, STOP,
            STOP, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 우승자가_2명인_경우_2명_모두_출력한다() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "5");

                assertThat(output()).contains(
                    "pobi : -", "woni : ", "jun : ",
                    "pobi : --", "woni : -", "jun : -",
                    "pobi : --", "woni : --", "jun : --",
                    "pobi : --", "woni : ---", "jun : ---",
                    "pobi : --", "woni : ----", "jun : ----",
                    "최종 우승자 : woni, jun");
            },
            MOVING_FORWARD, STOP, STOP,
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 우승자가_2명_이상인_경우_모두_출력한다() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "5");

                assertThat(output()).contains(
                    "pobi : -", "woni : ", "jun : ",
                    "pobi : --", "woni : -", "jun : -",
                    "pobi : ----", "woni : --", "jun : --",
                    "pobi : ----", "woni : ---", "jun : ---",
                    "pobi : ----", "woni : ----", "jun : ----",
                    "최종 우승자 : pobi, woni, jun");
            },
            MOVING_FORWARD, STOP, STOP,
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
            MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,
            STOP, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 자동차_이름이_5자를_초과하는_경우_예외를_던진다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름이_1자_미만인_경우_예외를_던진다1() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,", "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름이_1자_미만인_경우_예외를_던진다2() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(",pobi", "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름이_공백인_경우_예외를_던진다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(" ,pobi", "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름이_백스페이스인_경우_예외를_던진다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("\b,pobi", "3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름_문자열이_공백인_경우_예외를_던진다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("", "3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Test
    void 자동차_이름_문자열이_화이트스페이스인_경우_예외를_던진다() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(" ", "3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상이어야 합니다.")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
