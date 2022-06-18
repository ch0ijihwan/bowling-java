package model.frame;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.running.FirstPitch;

public class NormalFrame implements Frame {

    private static final int FIRST_INDEX = 1;
    private static final int LAST_INDEX_OF_NORMAL_FRAME = 9;
    private final int index;
    private BowlingState state;

    private NormalFrame(int index) {
        this.index = index;
        this.state = FirstPitch.create();
    }

    public static Frame createFirst() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame bowl(final PinCount pinCount) {
        this.state = state.bowl(pinCount);
        if (index == LAST_INDEX_OF_NORMAL_FRAME) {
            return LastFrame.create();
        }
        if (state.isEnd()) {
            return new NormalFrame(this.index + 1);
        }
        return this;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public String getScoreSymbol() {
        return this.state.getScoreSymbol();
    }
}
