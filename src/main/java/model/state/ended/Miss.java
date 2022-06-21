package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.status.Status;

public class Miss extends EndedState {

    private static final String DELIMITER = "|";
    private static final String ZERO_SYMBOL = "-";

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
        if(firstPinCount.getValue() == 0){
            return ZERO_SYMBOL + DELIMITER + secondPinCount.getValue();
        }
        return firstPinCount.getValue() + DELIMITER + secondPinCount.getValue();
    }

    @Override
    public boolean isSameStatus(final Status status) {
        return status == Status.MISS;
    }
}
