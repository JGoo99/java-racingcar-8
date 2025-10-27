package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.output.OutputView;

public class RaceResult {
    private final List<List<Car>> rounds;
    private final List<Car> finalRound;

    public RaceResult(List<List<Car>> rounds) {
        this.rounds = rounds;
        this.finalRound = rounds.getLast();
    }

    private List<Car> getWinners() {
        Car farthestCar = findFarthestCar();
        return finalRound.stream()
            .filter(car -> car.isSamePosition(farthestCar))
            .collect(Collectors.toList());
    }

    private Car findFarthestCar() {
        return finalRound.stream().max(AHEAD_ORDER)
            .orElseThrow(() -> new IllegalArgumentException("우승자를 찾을 수 없습니다."));
    }

    private static final Comparator<Car> AHEAD_ORDER = (left, right) -> {
        if (left.isAheadOf(right)) {
            return 1;
        }
        if (right.isAheadOf(left)) {
            return -1;
        }
        return 0;
    };

    public void printWith(OutputView out) {
        out.printRounds(this.rounds);
        out.printWinner(this.getWinners());
    }
}
