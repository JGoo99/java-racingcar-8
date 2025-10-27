package racingcar.output;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class OutputView {
    public void askCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void askAttempts() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public void printRounds(List<List<Car>> snapshots) {
        System.out.println("\n실행 결과");
        snapshots.forEach(this::printRound);
    }

    private void printRound(List<Car> cars) {
        cars.forEach(car -> System.out.println(car.formatRoundResult()));
        System.out.println();
    }

    public void printWinner(List<Car> winners) {
        String joined = winners.stream().map(Car::getName).collect(Collectors.joining(", "));
        System.out.println("최종 우승자 : " + joined);
    }
}
