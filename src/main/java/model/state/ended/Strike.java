package model.state.ended;

import model.state.BowlingState;

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
    public String toString() {
        return super.toString();
    }
}
