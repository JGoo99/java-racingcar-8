package racingcar.service;

import java.util.List;
import racingcar.domain.RaceResult;
import racingcar.input.AttemptParser;
import racingcar.input.CarNameParser;
import racingcar.input.InputView;
import racingcar.output.OutputView;

public class GameRunner {
    private final InputView in;
    private final OutputView out;
    private final CarNameParser carNameParser;
    private final AttemptParser attemptParser;
    private final RaceGame game;

    public GameRunner(InputView in, OutputView out, CarNameParser carNameParser,
                      AttemptParser attemptParser, RaceGame game) {
        this.in = in;
        this.out = out;
        this.carNameParser = carNameParser;
        this.attemptParser = attemptParser;
        this.game = game;
    }

    public void run() {
        final List<String> carNames = getCarNames();
        final int attempts = getAttempts();
        RaceResult result = game.run(carNames, attempts);
        result.printWith(out);
    }

    private int getAttempts() {
        out.askAttempts();
        String rawAttempts = in.readTrimmedLine();
        return attemptParser.parse(rawAttempts);
    }

    private List<String> getCarNames() {
        out.askCarNames();
        String rawNames = in.readTrimmedLine();
        return carNameParser.parse(rawNames);
    }
}
