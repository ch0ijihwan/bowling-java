package model.state.running;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.state.BowlingState;
import model.state.status.Status;

public abstract class RunningState implements BowlingState {
    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Status getStatus() {
        return Status.RUNNING;
    }

    @Override
    public Score createScore() {
        throw new NotCountScore("투구 실행 중에는 점수를 확인 할 수 없습니다.");
    }
}
