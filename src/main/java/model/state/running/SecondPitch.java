package model.state.running;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.ended.Miss;
import model.state.ended.Spare;

public class SecondPitch extends RunningState {

    private static final int MAXIMUM_PIN_COUNT = 10;
    private static final String ZERO_SYMBOL = "-";

    private final PinCount firstPinCount;

    public static RunningState create(final PinCount pinCount) {
        return new SecondPitch(pinCount);
    }

    private SecondPitch(final PinCount firstPinCount) {
        this.firstPinCount = firstPinCount;
    }

    @Override
    public BowlingState bowl(final PinCount secondPinCount) {
        validateSecondPitching(secondPinCount);
        if (firstPinCount.isSpare(secondPinCount)) {
            return Spare.create(firstPinCount, secondPinCount);
        }
        return Miss.create(firstPinCount, secondPinCount);
    }

    private void validateSecondPitching(final PinCount secondPinCount) {
        if (totalPinCount(secondPinCount) > MAXIMUM_PIN_COUNT) {
            throw new IllegalArgumentException("첫 번째, 투구와 두번째 투구의 쓰러진 핀의 갯수는 10이 넘으면 안됩니다.");
        }
    }

    private int totalPinCount(final PinCount secondPinCount) {
        return secondPinCount.getValue() + firstPinCount.getValue();
    }

    @Override
    public String getScoreSymbol() {
        if (firstPinCount.getValue() == 0) {
            return ZERO_SYMBOL;
        }
        return String.valueOf(firstPinCount.getValue());
    }

    @Override
    public Score calculateScore(final Score currentScore) {
        Score sumScore = this.firstPinCount.sumScore(currentScore);
        if (sumScore.hasNumberOfRemainingBonusCount(0)) {
            return sumScore;
        }
        throw new NotCountScore("투구 중인 상태에서는 점수를 확인 할 수 없습니다.");
    }
}
