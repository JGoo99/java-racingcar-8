package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CarMovePolicyTest {

    @Test
    void 이동_정책은_true와_false가_모두_관측되어야_한다() {
        // given
        CarMovePolicy policy = new CarMovePolicy();

        // when
        int trials = 10_000;
        int moveCount = 0;
        for (int i = 0; i < trials; i++) {
            if (policy.canMove()) {
                moveCount++;
            }
        }

        assertThat(moveCount).isGreaterThan(0);
        assertThat(moveCount).isLessThan(trials);
    }
}