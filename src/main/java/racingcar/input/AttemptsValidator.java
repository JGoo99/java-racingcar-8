package racingcar.input;

public class AttemptsValidator {
    public void validate(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도할 횟수는 1회 이상이어야 합니다.");
        }
    }
}
