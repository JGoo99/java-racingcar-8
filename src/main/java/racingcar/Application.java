package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String input = Console.readLine();
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("자동차 이름을 입력해야 합니다.");
            }

            String[] carNames = input.split(",", -1);
            Map<String, Integer> raceResult = Arrays.stream(carNames)
                .map(String::trim)
                .peek(x -> {
                    if (x.isEmpty()) {
                        throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
                    }
                    if (x.length() > 5) {
                        throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
                    }
                })
                .collect(Collectors.toMap(
                    name -> name,
                    name -> 0,
                    (a, b) -> {
                        throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
                    },
                    LinkedHashMap::new
                ));

            int[] values = new int[carNames.length];

            System.out.println("시도할 횟수는 몇 회인가요?");
            int attempts;
            try {
                String rawAttempts = Console.readLine();
                if (rawAttempts == null) {
                    throw new IllegalArgumentException("시도할 횟수 입력값이 비어 있습니다.");
                }
                attempts = Integer.parseInt(rawAttempts.trim());
                if (attempts <= 0) {
                    throw new IllegalArgumentException("시도할 횟수는 1회 이상이어야 합니다.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("시도할 횟수 입력값이 유효하지 않습니다. (숫자만 입력)");
            }

            StringBuilder logStr = new StringBuilder();
            logStr.append("\n실행 결과");
            for (int i = 0; i < attempts; i++) {
                logStr.append("\n");
                for (Entry<String, Integer> cur : raceResult.entrySet()) {
                    int random = Randoms.pickNumberInRange(0, 9);
                    if (random >= 4) {
                        cur.setValue(cur.getValue() + 1);
                    }
                    logStr.append(cur.getKey() + " : " + "-".repeat(cur.getValue()) + "\n");
                }
            }
            System.out.print(logStr);

            System.out.print("\n최종 우승자 : ");
            int max = Collections.max(raceResult.values());

            List<String> winners = raceResult.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Entry::getKey)
                .collect(Collectors.toList());
            System.out.println(String.join(", ", winners));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
