package racingcar.domain;

import java.util.Objects;

public class Car {
    private final String name;
    private int position;

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void moveIf(MovePolicy movePolicy) {
        if (movePolicy.canMove()) {
            this.position++;
        }
    }

    public String formatRoundResult() {
        return this.name + " : " + "-".repeat(this.position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Car car)) {
            return false;
        }
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Car copy() {
        return new Car(name, position);
    }

    public boolean isAtPosition(int max) {
        return this.position == max;
    }
}
