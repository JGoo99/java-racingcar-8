package racingcar.input;

public class AttemptParser {
    public int parse(String rawAttempts) {
        try {
            return parseAttempts(rawAttempts);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수 입력값이 유효하지 않습니다. (숫자만 입력)");
        }
    }

    private static int parseAttempts(String rawAttempts) {
        validate(rawAttempts);
        return Integer.parseInt(rawAttempts.trim());
    }

    private static void validate(String rawAttempts) {
        if (rawAttempts == null || rawAttempts.trim().isEmpty()) {
            throw new IllegalArgumentException("시도할 횟수 입력값이 비어 있습니다.");
        }
        if (!rawAttempts.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("시도 횟수는 자연수만 입력하세요.");
        }
    }
}
