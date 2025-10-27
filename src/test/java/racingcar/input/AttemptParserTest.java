package racingcar.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AttemptParserTest {
    private final AttemptParser parser = new AttemptParser();

    @Test
    void 올바른_자연수를_입력하면_해당_값이_반환된다() {
        // given // when // then
        assertThat(parser.parse("1")).isEqualTo(1);
        assertThat(parser.parse("123")).isEqualTo(123);
        assertThat(parser.parse("999999999")).isEqualTo(999_999_999);
    }

    @Test
    void 입력값이_0이면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse("0"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1회 이상");
    }

    @Test
    void 음수를_입력하면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse("-5"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("자연수만");
    }

    @Test
    void 선행_0이_포함된_입력은_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse("01"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("앞자리에 0");
    }

    @Test
    void 숫자가_아닌_문자가_포함되면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse("1a2"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("자연수만");
    }

    @Test
    void 빈_문자열을_입력하면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("비었습니다");
    }

    @Test
    void null을_입력하면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("비었습니다");
    }

    @Test
    void int_범위를_초과하는_큰_수를_입력하면_예외가_발생한다() {
        // given
        String overMax = String.valueOf((long) Integer.MAX_VALUE + 1);

        // when // then
        assertThatThrownBy(() -> parser.parse(overMax))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("정수 범위를 초과");
    }

}