package model.state.ended;

import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.status.Status;

public class Miss extends EndedState {

    private static final String DELIMITER = "|";
    private static final String ZERO_SYMBOL = "-";

    private final PinCount firstPinCount;
    private final PinCount secondPinCount;

    public static BowlingState create(final PinCount firstPinCount, final PinCount secondPinCount) {
        return new Miss(firstPinCount, secondPinCount);
    }

    private Miss(final PinCount firstPinCount, final PinCount secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    @Override
    public String getScoreSymbol() {
        if (firstPinCount.getValue() == 0 || secondPinCount.getValue() == 0) {
            return changeGutterFormat(firstPinCount) + DELIMITER + changeGutterFormat(secondPinCount);
        }
        return firstPinCount.getValue() + DELIMITER + secondPinCount.getValue();
    }

    private String changeGutterFormat(final PinCount pinCount) {
        if (pinCount.getValue() == 0) {
            return ZERO_SYMBOL;
        }
        return String.valueOf(pinCount.getValue());
    }

    @Override
    public Status getStatus() {
        return Status.MISS;
    }

    @Override
    public Score createScore() {
        return Score.createMissScore(getSumPinCount());
    }

    private int getSumPinCount() {
        return firstPinCount.getValue() + secondPinCount.getValue();
    }

    @Override
    public Score calculateScore(final Score currentScore) {
        Score addFirstPinCountScore = firstPinCount.sumScore(currentScore);
        if (addFirstPinCountScore.hasNumberOfRemainingBonusCount(0)) {
            return addFirstPinCountScore;
        }
        return secondPinCount.sumScore(addFirstPinCountScore);
    }
}
