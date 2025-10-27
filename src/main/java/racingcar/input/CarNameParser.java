package racingcar.input;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {

    private static final int MAX_NAME_LENGTH = 5;

    public List<String> parse(String rawNames) {
        validateBlank(rawNames);
        List<String> carNames = Arrays.stream(rawNames.split(",", -1))
            .map(String::trim)
            .toList();
        validate(carNames);
        return carNames;
    }

    private void validateBlank(String rawNames) {
        if (rawNames == null || rawNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름 입력이 비었습니다.");
        }
    }

    private void validate(List<String> carNames) {
        validateNotEmptyList(carNames);
        carNames.forEach(this::validateSingleName);
        ensureNoDuplicates(carNames);
    }

    private static void validateNotEmptyList(List<String> carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }

    private void validateSingleName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
        }

        if (name.contains(",") || name.contains("\n") || name.contains("\r")) {
            throw new IllegalArgumentException("이름에 콤마/개행은 허용되지 않습니다.");
        }

        int len = name.codePointCount(0, name.length());
        if (len < 1 || len > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 1~5글자여야 합니다.");
        }
    }

    private void ensureNoDuplicates(List<String> names) {
        long distinct = names.stream().distinct().count();
        if (distinct != names.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }
}
