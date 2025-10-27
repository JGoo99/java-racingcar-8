package racingcar.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readTrimmedLine() {
        return Console.readLine().trim();
    }
}