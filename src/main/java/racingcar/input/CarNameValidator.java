package racingcar.input;

import java.util.List;

public class CarNameValidator {
    public void validate(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
        }
    }
}
