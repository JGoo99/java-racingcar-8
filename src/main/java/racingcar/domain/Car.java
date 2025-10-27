package racingcar.domain;

import java.util.Objects;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int position;

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Car(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상이어야 합니다.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하이어야 합니다.");
        }
        this.name = name;
        this.position = 0;
    }

    public void move() {
        this.position++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        Car car = (Car) obj;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
