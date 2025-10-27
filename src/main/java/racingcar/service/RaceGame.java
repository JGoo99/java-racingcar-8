package racingcar.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        List<Map<String, Integer>> rounds = new ArrayList<>();

        for (int i = 0; i < attempts; i++) {
            for (Car car : cars) {
                if (shouldMove()) {
                    car.move();
                }
                rounds.add(parseSnapshot(cars));
            }
        }
        return new RaceResult(rounds, parseSnapshot(cars));
    }

    private Map<String, Integer> parseSnapshot(List<Car> cars) {
        Map<String, Integer> snapShot = new LinkedHashMap<>();
        for (Car car : cars) {
            snapShot.put(car.getName(), car.getPosition());
        }
        return snapShot;
    }

    private static List<Car> initGameBoard(List<String> carNames) {
        if (carNames.size() != carNames.stream().distinct().count()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
        return carNames.stream().map(Car::new).collect(Collectors.toList());
    }

    private boolean shouldMove() {
        return this.movePolicy.canMove();
    }
}
