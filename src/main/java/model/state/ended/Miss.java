package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;

public class Miss extends EndedState {

    private static final String DELIMITER = "|";

    private final PinCount firstPinCount;
    private final PinCount secondPinCount;

    public static BowlingState create(final PinCount firstPinCount, final PinCount secondPinCount){
        return new Miss(firstPinCount, secondPinCount);
    }

    private Miss(final PinCount firstPinCount, final PinCount secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    @Override
    public String getScoreSymbol() {
        return firstPinCount.getValue() + DELIMITER + secondPinCount.getValue();
    }
}
