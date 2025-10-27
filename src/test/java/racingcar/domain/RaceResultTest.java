package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class RaceResultTest {

    @Test
    void 가장_멀리_간_자동차가_우승자로_선정된다() {
        // given
        Car pobi = new Car("pobi", 3);
        Car jun = new Car("jun", 5);
        Car woni = new Car("woni", 4);
        List<List<Car>> rounds = List.of(List.of(pobi, jun, woni));

        RaceResult result = new RaceResult(rounds);

        // when
        List<Car> winners = extractWinners(result);

        // then
        assertThat(winners)
            .hasSize(1)
            .first()
            .extracting(Car::getName)
            .isEqualTo("jun");
    }

    @Test
    void 가장_멀리_간_자동차가_여러_대면_공동_우승자로_선정된다() {
        // given
        Car pobi = new Car("pobi", 4);
        Car jun = new Car("jun", 4);
        Car woni = new Car("woni", 3);
        List<List<Car>> rounds = List.of(List.of(pobi, jun, woni));

        RaceResult result = new RaceResult(rounds);

        // when
        List<Car> winners = extractWinners(result);

        // then
        assertThat(winners)
            .extracting(Car::getName)
            .containsExactlyInAnyOrder("pobi", "jun");
    }

    private List<Car> extractWinners(RaceResult result) {
        try {
            var method = RaceResult.class.getDeclaredMethod("getWinners");
            method.setAccessible(true);
            return (List<Car>) method.invoke(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}