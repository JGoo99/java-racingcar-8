package racingcar.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readTrimmedLine() {
        String input = Console.readLine();
        if (input == null) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
        return input.trim();
    }
}