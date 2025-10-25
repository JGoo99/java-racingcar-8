package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import racingcar.domain.RaceResult;

public class RaceGame {
    public RaceResult run(List<String> carNames, int attempts) {
        Map<String, Integer> board = initGameBoard(carNames);

        List<Map<String, Integer>> rounds = new ArrayList<>();
        for (int i = 0; i < attempts; i++) {
            for (Entry<String, Integer> cur : board.entrySet()) {
                if (shouldMove()) {
                    cur.setValue(cur.getValue() + 1);
                }
            }
            rounds.add(new LinkedHashMap<>(board));
        }

        for (int i = 0; i < attempts; i++) {
            for (Entry<String, Integer> cur : board.entrySet()) {
                if (shouldMove()) {
                    cur.setValue(cur.getValue() + 1);
                }
            }
        }
        return new RaceResult(rounds, board);
    }

    private static Map<String, Integer> initGameBoard(List<String> carNames) {
        Map<String, Integer> board = new LinkedHashMap<>();
        for (String carName : carNames) {
            board.put(carName, 0);
        }
        return board;
    }

    private static Map<String, Integer> initCars(List<String> carNames) {
        Map<String, Integer> raceResult = new LinkedHashMap<>();
        for (String carName : carNames) {
            if (carName.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
            }
            if (carName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
            }
            if (raceResult.containsKey(carName)) {
                throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
            }
            raceResult.put(carName, 0);
        }
        return raceResult;
    }

    private static boolean shouldMove() {
        int random = Randoms.pickNumberInRange(0, 9);
        return random >= 4;
    }
}
