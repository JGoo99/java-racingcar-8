package racingcar;

import racingcar.domain.CarMovePolicy;
import racingcar.input.AttemptParser;
import racingcar.input.CarNameParser;
import racingcar.input.InputView;
import racingcar.output.OutputView;
import racingcar.service.RaceGame;

public class Application {
    public static void main(String[] args) {
        GameRunner runner = new GameRunner(
            new InputView(),
            new OutputView(),
            new CarNameParser(),
            new AttemptParser(),
            new RaceGame(new CarMovePolicy()));
        runner.run();
    }
}
