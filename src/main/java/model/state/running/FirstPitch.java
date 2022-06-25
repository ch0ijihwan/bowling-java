package model.state.running;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
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

    @Override
    public Score addScore(final Score currentScore) {
        throw new NotCountScore("투구 중인 상태에서는 점수를 확인 할 수 없습니다.");
    }
}
