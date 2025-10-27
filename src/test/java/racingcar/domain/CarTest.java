package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CarTest {
    @Test
    void 이동_조건이_true_일_때_자동차는_전진한다() {
        // given
        Car car = new Car("pobi", 0);
        MovePolicy alwaysMove = () -> true;

        // when
        car.moveIf(alwaysMove);

        // then
        assertThat(car.formatRoundResult()).isEqualTo("pobi : -");
    }

    @Test
    void 이동_조건이_false_일_때_자동차는_전진하지_않는다() {
        // given
        Car car = new Car("pobi", 0);
        MovePolicy neverMove = () -> false;

        // when
        car.moveIf(neverMove);

        // then
        assertThat(car.formatRoundResult()).isEqualTo("pobi : ");
    }

    @Test
    void 다른_자동차보다_앞서_있으면_true를_반환한다() {
        // given
        Car pobi = new Car("pobi", 3);
        Car woni = new Car("woni", 1);

        // when // then
        assertThat(pobi.isAheadOf(woni)).isTrue();
        assertThat(woni.isAheadOf(pobi)).isFalse();
    }

    @Test
    void 같은_위치에_있으면_isSamePosition은_true를_반환한다() {
        // given
        Car pobi = new Car("pobi", 2);
        Car jun = new Car("jun", 2);

        // when // then
        assertThat(pobi.isSamePosition(jun)).isTrue();
    }

    @Test
    void formatRoundResult는_현재_위치만큼_하이픈을_출력한다() {
        // given
        Car car = new Car("racing", 3);

        // when // then
        assertThat(car.formatRoundResult()).isEqualTo("racing : ---");
    }

    @Test
    void copy는_같은_값을_가지지만_서로_다른_객체를_반환한다() {
        // given
        Car original = new Car("pobi", 2);

        // when
        Car copy = original.copy();

        // then
        assertThat(copy).isEqualTo(original);
        assertThat(copy).isNotSameAs(original);
        assertThat(copy.formatRoundResult()).isEqualTo("pobi : --");
    }

    @Test
    void equals와_hashCode는_이름이_같으면_동일하게_판단한다() {
        // given
        Car c1 = new Car("pobi", 0);
        Car c2 = new Car("pobi", 5);
        Car c3 = new Car("woni", 0);

        // when // then
        assertThat(c1).isEqualTo(c2);
        assertThat(c1).hasSameHashCodeAs(c2);
        assertThat(c1).isNotEqualTo(c3);
    }
}