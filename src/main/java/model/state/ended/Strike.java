package model.state.ended;

import model.state.BowlingState;
import model.state.status.Status;

public class Strike extends EndedState {

    private static final String STRIKE_SYMBOL = "X";

    public static BowlingState create() {
        return new Strike();
    }

    @Override
    public String getScoreSymbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public boolean isSameStatus(final Status status) {
        return status == Status.STRIKE;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
