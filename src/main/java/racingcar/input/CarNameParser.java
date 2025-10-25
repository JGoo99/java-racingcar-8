package racingcar.input;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {
    public List<String> parse(String rawNames) {
        return Arrays.stream(rawNames.split(",", -1))
            .map(String::trim)
            .toList();
    }
}
