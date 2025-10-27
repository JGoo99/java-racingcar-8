package racingcar.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class CarNameParserTest {

    private final CarNameParser parser = new CarNameParser();

    @Test
    void 유효한_자동차_이름을_입력하면_리스트로_파싱된다() {
        // given
        String raw = "pobi,woni,jun";

        // when
        List<String> result = parser.parse(raw);

        // then
        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @Test
    void 입력값이_null이거나_빈문자열이면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("비었습니다");

        assertThatThrownBy(() -> parser.parse(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("비었습니다");
    }

    @Test
    void 자동차_이름이_비어있거나_공백만_있으면_예외가_발생한다() {
        // given
        String raw = "pobi,,jun";

        // when // then
        assertThatThrownBy(() -> parser.parse(raw))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1자 이상");
    }

    @Test
    void 자동차_이름이_6글자_이상이면_예외가_발생한다() {
        // given
        String raw = "abcdef";

        // when // then
        assertThatThrownBy(() -> parser.parse(raw))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1~5글자");
    }

    @Test
    void 자동차_이름에_콤마나_개행이_포함되면_예외가_발생한다() {
        // given // when // then
        assertThatThrownBy(() -> parser.parse("po\nbi"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("개행");
    }

    @Test
    void 자동차_이름이_중복되면_예외가_발생한다() {
        // given
        String raw = "pobi,woni,pobi";

        // when // then
        assertThatThrownBy(() -> parser.parse(raw))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복");
    }

    @Test
    void 이름_양끝의_공백은_trim되어_파싱된다() {
        // given
        String raw = "  pobi ,  woni  ";

        // when
        List<String> result = parser.parse(raw);

        // then
        assertThat(result).containsExactly("pobi", "woni");
    }
}