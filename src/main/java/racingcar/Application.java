package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String input = Console.readLine();
            String[] carNames = input.split(",", -1);
            int[] values = new int[carNames.length];
            if (Arrays.stream(carNames).anyMatch(x -> x.trim().isBlank())) {
                throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
            }
            if (Arrays.stream(carNames).anyMatch(x -> x.length() > 5)) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
            }

            System.out.println("시도할 횟수는 몇 회인가요?");
            int x = Integer.parseInt(Console.readLine());

            StringBuilder logStr = new StringBuilder();
            logStr.append("\n실행 결과");
            for (int i = 0; i < x; i++) {
                logStr.append("\n");
                for (int j = 0; j < carNames.length; j++) {
                    int random = Randoms.pickNumberInRange(0, 9);
                    if (random >= 4) {
                        values[j]++;
                    }
                    logStr.append(carNames[j] + " : " + "-".repeat(values[j]) + "\n");
                }
            }
            System.out.print(logStr);

            System.out.print("\n최종 우승자 : ");
            int max = Arrays.stream(values).max().getAsInt();

            List<String> winners = new ArrayList<>();
            for (int i = 0; i < carNames.length; i++) {
                if (values[i] >= max) {
                    winners.add(carNames[i]);
                }
            }
            System.out.println(String.join(", ", winners));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
