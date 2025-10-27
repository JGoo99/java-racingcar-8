package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.MovePolicy;
import racingcar.domain.RaceResult;
import racingcar.output.OutputView;

class RaceGameTest {

    static class SpyOutputView extends OutputView {
        List<List<Car>> roundsCaptured;

        @Override
        public void printRounds(List<List<Car>> snapshots) {
            this.roundsCaptured = snapshots;
        }
    }

    @Test
    void 시도_횟수만큼_라운드_스냅샷이_생성된다() {
        // given
        MovePolicy alwaysMove = () -> true;
        RaceGame game = new RaceGame(alwaysMove);
        List<String> carNames = List.of("pobi", "woni");
        int attempts = 3;

        // when
        RaceResult result = game.run(carNames, attempts);

        // then
        SpyOutputView out = new SpyOutputView();
        result.printWith(out);

        assertThat(out.roundsCaptured).hasSize(attempts);
    }

}