package model.state.running;

import model.state.BowlingState;
import model.state.status.Status;

public abstract class RunningState implements BowlingState {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isSameStatus(final Status status){
        return status == Status.RUNNING;
    }
}
