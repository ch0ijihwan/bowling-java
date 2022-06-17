package model.pin;

import java.util.Objects;

public class PinCount {

    private static final int MAXIMUM_PIN_COUNT = 10;
    private static final int MINIMUM_PIN_COUNT = 0;

    public final int value;

    public PinCount(final int pinCount) {
        validateSize(pinCount);

        this.value = pinCount;
    }

    private void validateSize(final int pinCount) {
        if (pinCount < MINIMUM_PIN_COUNT || MAXIMUM_PIN_COUNT < pinCount) {
            throw new IllegalArgumentException("쓰러트린 볼링 핀의 갯수는 10 이하의 양수입니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isStrike() {
        return value == MAXIMUM_PIN_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinCount pinCount1 = (PinCount) o;
        return value == pinCount1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PinCount{" +
                "value=" + value +
                '}';
    }
}
