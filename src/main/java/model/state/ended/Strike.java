package model.state.ended;

import model.frame.score.Score;
import model.state.BowlingState;
import model.state.status.Status;

public class Strike extends EndedState {

    private static final String STRIKE_SYMBOL = "X";

    public static BowlingState create() {
        return new Strike();
    }

    @Override
    public String getScoreSymbol() {
        return STRIKE_SYMBOL;
    }

    @Override
    public Status getStatus() {
        return Status.STRIKE;
    }

    @Override
    public Score createScore() {
        return Score.createStrikeScore();
    }

    @Override
    public Score calculateScore(final Score currentScore) {
       if (!currentScore.hasRemainingBonusCount()){
           return currentScore;
       }
        return currentScore.bowl(10);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
