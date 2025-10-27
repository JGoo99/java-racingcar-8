package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.MovePolicy;
import racingcar.domain.RaceResult;

public class RaceGame {
    private final MovePolicy movePolicy;

    public RaceGame(MovePolicy movePolicy) {
        this.movePolicy = movePolicy;
    }

    public RaceResult run(List<String> carNames, int attempts) {
        List<Car> cars = initGameBoard(carNames);
        List<List<Car>> rounds = new ArrayList<>();

        for (int i = 0; i < attempts; i++) {
            for (Car car : cars) {
                car.moveIf(movePolicy);
            }
            rounds.add(copyCars(cars));
        }
        return new RaceResult(rounds);
    }

    private List<Car> copyCars(List<Car> cars) {
        return cars.stream()
            .map(Car::copy)
            .toList();
    }

    private static List<Car> initGameBoard(List<String> carNames) {
        return carNames.stream()
            .map(name -> new Car(name, 0)).collect(Collectors.toList());
    }
}
