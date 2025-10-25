package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RaceResult {
    private final List<Map<String, Integer>> rounds;
    private final Map<String, Integer> finalBoard;

    public RaceResult(List<Map<String, Integer>> rounds, Map<String, Integer> finalBoard) {
        this.rounds = rounds;
        this.finalBoard = finalBoard;
    }

    public List<Map<String, Integer>> getRounds() {
        return rounds;
    }

    public List<String> getWinners() {
        int max = Collections.max(finalBoard.values());
        return finalBoard.entrySet().stream()
            .filter(e -> e.getValue() == max)
            .map(Entry::getKey)
            .collect(Collectors.toList());
    }
}
