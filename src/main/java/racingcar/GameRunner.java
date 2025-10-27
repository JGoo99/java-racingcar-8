package racingcar;

import java.util.List;
import racingcar.domain.CarMovePolicy;
import racingcar.domain.RaceResult;
import racingcar.input.AttemptParser;
import racingcar.input.CarNameParser;
import racingcar.input.InputView;
import racingcar.output.OutputView;
import racingcar.service.RaceGame;

public class GameRunner {
    private final InputView in = new InputView();
    private final OutputView out = new OutputView();
    private final CarNameParser carNameParser = new CarNameParser();
    private final AttemptParser attemptParser = new AttemptParser();
    private final RaceGame game = new RaceGame(new CarMovePolicy());

    public void run() {
        out.askCarNames();
        String rawNames = in.readTrimmedLine();
        final List<String> carNames = carNameParser.parse(rawNames);

        out.askAttempts();
        String rawAttempts = in.readTrimmedLine();
        final int attempts = attemptParser.parse(rawAttempts);

        RaceResult result = game.run(carNames, attempts);
        result.printWith(out);
    }
}
