package racingcar.input;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarNameValidator {
    private static final int MAX_LENGTH_OF_CAR_NAME = 5;

    public void validate(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }

        Set<String> seen = new HashSet<>();
        for (String carName : carNames) {
            if (carName.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
            }
            if (carName.length() > MAX_LENGTH_OF_CAR_NAME) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
            }
            if (!seen.add(carName)) {
                throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
            }
        }
    }
}
