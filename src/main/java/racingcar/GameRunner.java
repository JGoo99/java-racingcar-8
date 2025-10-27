package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import racingcar.domain.CarMovePolicy;
import racingcar.domain.RaceResult;
import racingcar.input.AttemptParser;
import racingcar.input.AttemptsValidator;
import racingcar.input.CarNameParser;
import racingcar.input.CarNameValidator;
import racingcar.input.InputView;
import racingcar.output.OutputView;
import racingcar.service.RaceGame;

public class GameRunner {
    private final InputView in = new InputView();
    private final OutputView out = new OutputView();
    private final CarNameParser carNameParser = new CarNameParser();
    private final AttemptParser attemptParser = new AttemptParser();
    private final CarNameValidator carNameValidator = new CarNameValidator();
    private final AttemptsValidator attemptsValidator = new AttemptsValidator();
    private final RaceGame game = new RaceGame(new CarMovePolicy());

    public void run() {
        out.askCarNames();
        String rawNames = in.readTrimmedLineRequired();
        final List<String> carNames = carNameParser.parse(rawNames);
        carNameValidator.validate(carNames);

        out.askAttempts();
        String rawAttempts = in.readTrimmedLineRequired();
        final int attempts = attemptParser.parse(rawAttempts);
        attemptsValidator.validate(attempts);

        RaceResult result = game.run(carNames, attempts);
        out.printRounds(result.getRounds());
        out.printWinner(result.getWinners());
    }
}
