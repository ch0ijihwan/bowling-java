package model.frame;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.running.FirstPitch;

public class NormalFrame implements Frame {

    private static final int FIRST_INDEX = 1;
    private static final int LAST_INDEX_OF_NORMAL_FRAME = 9;

    private final int index;
    private BowlingState state;
    private  Frame nextFrame;

    private NormalFrame(final int index) {
        this.index = index;
        this.state = FirstPitch.create();
    }

    public static Frame createFirst() {
        return new NormalFrame(FIRST_INDEX);
    }

    @Override
    public Frame bowl(final PinCount pinCount) {
        this.state = state.bowl(pinCount);

        if (state.isEnd()) {
            this.nextFrame = createNextFrame();
            return this.nextFrame;
        }
        return this;
    }

    private Frame createNextFrame() {
        if(this.index == LAST_INDEX_OF_NORMAL_FRAME){
            return LastFrame.create();
        }
        return new NormalFrame(this.index + 1);
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

    @Override
    public int getScore() throws NotCountScore {
        try {
            return calculateScore().getScoreValue();
        } catch (NotCountScore e) {
            return Score.createCanNotCalculateScore().getScoreValue();
        }
    }

    private Score calculateScore() throws NotCountScore {
        Score score = state.createScore();
        if (!hasBonusChance(score)) {
            return score;
        }
        return nextFrame.addScore(score);

    }

    private boolean hasBonusChance(final Score score) {
        return score.hasRemainingBonusCount();
    }

    @Override
    public Score addScore(final Score currentScore) throws NotCountScore{
        Score score = state.calculateScore(currentScore);
        if (!hasBonusChance(score)) {
            return score;
        }
        return nextFrame.addScore(score);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", state=" + state +
                '}';
    }
}
