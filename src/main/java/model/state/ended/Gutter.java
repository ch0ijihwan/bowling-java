package model.state.ended;

import model.state.BowlingState;

public class Gutter extends EndedState {

    private static final String GUTTER_SYMBOL = "- -";

    public static BowlingState create() {
        return new Gutter();
    }

    @Override
    public String getScoreSymbol() {
        return GUTTER_SYMBOL;
    }
}
