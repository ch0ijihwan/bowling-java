package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;

import java.util.Objects;

public class Spare extends EndedState {

    private static final String DELIMITER = "|";
    private static final String SPARE_SYMBOL = "/";

    private final PinCount firstPinCount;
    private final PinCount secondPinCount;

    public static BowlingState create(final PinCount firstPinCount, final PinCount secondPinCount) {
        return new Spare(firstPinCount, secondPinCount);
    }

    private Spare(PinCount firstPinCount, PinCount secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    @Override
    public String getScoreSymbol() {
        return firstPinCount.getValue() + DELIMITER + SPARE_SYMBOL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPinCount, spare.firstPinCount) && Objects.equals(secondPinCount, spare.secondPinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPinCount, secondPinCount);
    }

    @Override
    public String toString() {
        return "Spare{" +
                "firstPinCount=" + firstPinCount +
                ", secondPinCount=" + secondPinCount +
                '}';
    }
}
