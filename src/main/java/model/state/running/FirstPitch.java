package model.state.running;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.ended.Strike;

public class FirstPitch extends RunningState {

    private static final String BLANK = "";

    public static RunningState create() {
        return new FirstPitch();
    }

    private FirstPitch() {
    }

    @Override
    public BowlingState bowl(final PinCount pinCount) {
        if (pinCount.isStrike()) {
            return Strike.create();
        }
        return SecondPitch.create(pinCount);
    }

    @Override
    public String getScoreSymbol() {
        return BLANK;
    }
}
