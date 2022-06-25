package model.frame;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.running.FirstPitch;
import model.state.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

    private static final int MAXIMUM_BOWL_COUNT = 3;
    private static final int MINIMUM_BOWL_COUNT = 2;
    private static final int LAST_FRAME_INDEX = 10;

    private final List<BowlingState> states = new ArrayList<>();
    private int bowlCount;

    private LastFrame() {
        this.states.add(FirstPitch.create());
        this.bowlCount = 0;
    }

    public static Frame create() {
        return new LastFrame();
    }

    @Override
    public Frame bowl(final PinCount pinCount) {
        BowlingState current = getLastState();

        if (isStrikeOrSpare(current)) {
            bonusBowl(pinCount);
            return this;
        }

        states.remove(getStatesLastIndex());
        states.add(current.bowl(pinCount));
        bowlCount++;
        return this;
    }

    private void bonusBowl(final PinCount pinCount) {
        BowlingState bonusBowl = FirstPitch.create()
                .bowl(pinCount);
        states.add(bonusBowl);
        bowlCount++;
    }

    private int getStatesLastIndex() {
        return states.size() - 1;
    }

    private BowlingState getLastState() {
        return states.get(getStatesLastIndex());
    }

    private boolean isStrikeOrSpare(final BowlingState currentBowlingState) {
        return currentBowlingState.getStatus() == Status.STRIKE || currentBowlingState.getStatus() == Status.SPARE;
    }

    @Override
    public int getIndex() {
        return LAST_FRAME_INDEX;
    }

    @Override
    public boolean isEnd() {
        if (bowlCount == MAXIMUM_BOWL_COUNT) {
            return true;
        }

        BowlingState lastState = getLastState();
        return bowlCount == MINIMUM_BOWL_COUNT && lastState.getStatus() == Status.MISS;
    }

    @Override
    public String getScoreSymbol() {
        return states.stream()
                .map(BowlingState::getScoreSymbol)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int getScore() {
        try {
            return calculateScore().getScoreValue();
        } catch (NotCountScore e) {
            return Score.createCanNotCalculateScore().getScoreValue();
        }
    }

    private Score calculateScore() throws NotCountScore {
        Score score = states.get(0).createScore();
        for (int i = 1; i < states.size(); i++) {
            score = states.get(i).calculateScore(score);
        }
        return score;
    }


    @Override
    public Score addScore(final Score currentScore) {
        if(getLastState().isEnd()){
            return calculateScore();
        }
        throw new NotCountScore("마지막 라운드의 추가 투구 기회가 남아있습니다.");
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "states=" + states +
                ", bowlCount=" + bowlCount +
                '}';
    }
}
