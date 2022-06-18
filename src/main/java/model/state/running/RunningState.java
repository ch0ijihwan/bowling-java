package model.state.running;

import model.state.BowlingState;

public abstract class RunningState implements BowlingState {
    @Override
    public boolean isEnd() {
        return false;
    }
}
