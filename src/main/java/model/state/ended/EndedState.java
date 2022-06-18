package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;

public abstract class EndedState implements BowlingState {
    @Override
    public BowlingState bowl(PinCount knockedDownPinCount) {
        throw new IllegalStateException("이미 투구가 끝난 상태 입니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
