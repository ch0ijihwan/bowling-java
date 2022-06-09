package model.bowl;

import java.util.Objects;

public class PinCount {

    private static final int MINIMUM_VALUE_OF_PIN = 0;
    public static final int MAXIMUM_VALUE_OF_PIN = 10;

    private final int value;
    public PinCount(final int countOfPin) {
        if (countOfPin < MINIMUM_VALUE_OF_PIN || countOfPin > MAXIMUM_VALUE_OF_PIN) {
            throw new IllegalArgumentException("볼링 핀의 갯수는 최대 10인 양수입니다.");
        }
        this.value = countOfPin;
    }

    public int getValue(){
        return value;
    }

    public boolean isStrike() {
        return value == 10;
    }
    public boolean isSpare(final PinCount prePinCount) {
        return !prePinCount.isStrike() && (prePinCount.getValue() + this.getValue()) == MAXIMUM_VALUE_OF_PIN;
    }

    public boolean isGutter() {
        return this.getValue() == MINIMUM_VALUE_OF_PIN;
    }

    public int plus(PinCount pinCount) {
        return pinCount.getValue() + this.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinCount pinCount = (PinCount) o;
        return value == pinCount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
