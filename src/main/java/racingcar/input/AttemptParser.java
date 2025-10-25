package racingcar.input;

public class AttemptParser {
    public int parse(String rawAttempts) {
        int attempts;
        try {
            if (rawAttempts == null || rawAttempts.trim().isEmpty()) {
                throw new IllegalArgumentException("시도할 횟수 입력값이 비어 있습니다.");
            }
            attempts = Integer.parseInt(rawAttempts.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수 입력값이 유효하지 않습니다. (숫자만 입력)");
        }
        return attempts;
    }
}
