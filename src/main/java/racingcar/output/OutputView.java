package racingcar.output;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void askCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public void askAttempts() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public void printRounds(List<Map<String, Integer>> rounds) {
        StringBuilder roundResult = new StringBuilder();
        roundResult.append("\n실행 결과");
        for (Map<String, Integer> board : rounds) {
            for (Entry<String, Integer> cur : board.entrySet()) {
                roundResult.append(cur.getKey())
                    .append(" : ")
                    .append("-".repeat(cur.getValue()))
                    .append("\n");
            }
        }
        System.out.println(roundResult);
    }

    public void printWinner(List<String> winners) {
        System.out.println("\n최종 우승자 : " + String.join(", ", winners));
    }
}
