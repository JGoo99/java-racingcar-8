package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class CarMovePolicy implements MovePolicy {

    private static final int MOVE_THRESHOLD = 4;
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;

    @Override
    public boolean canMove() {
        int randomNumber = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
        return randomNumber >= MOVE_THRESHOLD;
    }
}
