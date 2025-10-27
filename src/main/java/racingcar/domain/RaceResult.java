package racingcar.domain;

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
        int max = getMaxDistance();
        return finalRound.stream()
            .filter(car -> car.isAtPosition(max))
            .collect(Collectors.toList());
    }

    private int getMaxDistance() {
        return finalRound.stream().mapToInt(Car::getPosition).max().getAsInt();
    }

    public void printWith(OutputView out) {
        out.printRounds(this.rounds);
        out.printWinner(this.getWinners());
    }
}
