package racingcar.output;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

class OutputViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final OutputView outputView = new OutputView();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void 자동차_이름_입력_요청문을_출력한다() {
        // when
        outputView.askCarNames();

        // then
        String output = outContent.toString().trim();
        assertThat(output)
            .isEqualTo("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    @Test
    void 시도_횟수_입력_요청문을_출력한다() {
        // when
        outputView.askAttempts();

        // then
        String output = outContent.toString().trim();
        assertThat(output).isEqualTo("시도할 횟수는 몇 회인가요?");
    }

    @Test
    void 각_라운드의_결과를_출력한다() {
        // given
        Car pobi = new Car("pobi", 2);
        Car jun = new Car("jun", 3);
        List<List<Car>> snapshots = List.of(List.of(pobi, jun));

        // when
        outputView.printRounds(snapshots);

        // then
        String output = outContent.toString();
        assertThat(output)
            .contains("실행 결과")
            .contains("pobi : --")
            .contains("jun : ---");
    }

    @Test
    void 최종_우승자를_콤마로_구분하여_출력한다() {
        // given
        Car pobi = new Car("pobi", 3);
        Car jun = new Car("jun", 3);
        List<Car> winners = List.of(pobi, jun);

        // when
        outputView.printWinner(winners);

        // then
        String output = outContent.toString().trim();
        assertThat(output).isEqualTo("최종 우승자 : pobi, jun");
    }
}