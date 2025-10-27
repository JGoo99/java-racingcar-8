package racingcar.input;

import java.util.regex.Pattern;

public class AttemptParser {
    private static final Pattern NATURAL_NUMBER = Pattern.compile("^[0-9]+$");

    public int parse(String rawAttempts) {
        validate(rawAttempts);
        int attempts = parseAttempts(rawAttempts);
        ensurePositive(attempts);
        return attempts;
    }

    private void validate(String rawAttempts) {
        requireNotBlank(rawAttempts);
        ensureNaturalNumberForm(rawAttempts);
        ensureNoLeadingZeros(rawAttempts);
    }

    private static void requireNotBlank(String raw) {
        if (raw == null || raw.isEmpty()) {
            throw new IllegalArgumentException("시도 횟수 입력이 비었습니다.");
        }
    }

    private void ensureNaturalNumberForm(String rawAttempts) {
        if (!NATURAL_NUMBER.matcher(rawAttempts).matches()) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 자연수만 입력하세요.");
        }
    }

    private static void ensureNoLeadingZeros(String s) {
        if (s.length() > 1 && s.startsWith("0")) {
            throw new IllegalArgumentException("시도 횟수의 앞자리에 0을 붙일 수 없습니다.");
        }
    }

    private static int parseAttempts(String rawAttempts) {
        try {
            return Integer.parseInt(rawAttempts);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수가 정수 범위를 초과했습니다.");
        }
    }

    private void ensurePositive(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도할 횟수는 1회 이상이어야 합니다.");
        }
    }
}
