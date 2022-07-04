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
    private static final int LAST_FRAME_INDEX = 9;
    private static final int FIRST_STATE_INDEX = 0;
    public static final int SECOND_STATE_INDEX = 1;
    private static final String DELIMITER = "|";

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
        BowlingState current = states.get(getStatesLastIndex());

        if (isStrikeOrSpare(current)) {
            bonusBowl(pinCount);
            return this;
        }

        states.remove(getStatesLastIndex());
        states.add(current.bowl(pinCount));
        bowlCount++;
        return this;
    }

    private int getStatesLastIndex() {
        return states.size() - 1;
    }

    private void bonusBowl(final PinCount pinCount) {
        BowlingState bonusBowl = FirstPitch.create()
                .bowl(pinCount);
        states.add(bonusBowl);
        bowlCount++;
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

        return bowlCount == MINIMUM_BOWL_COUNT && states.get(getStatesLastIndex()).getStatus() == Status.MISS;
    }

    @Override
    public String getScoreSymbol() {
        return states.stream()
                .map(BowlingState::getScoreSymbol)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public int getScore() {
        try {
            return calculateScore().getScoreValue();
        } catch (NotCountScore e) {
            return Score.createCanNotCalculateScore()
                    .getScoreValue();
        }
    }

    private Score calculateScore() throws NotCountScore {
        Score score = states.get(FIRST_STATE_INDEX)
                .createScore();
        for (BowlingState state : states) {
            score = state.calculateScore(score);
        }
        return score;
    }

    @Override
    public Score addScore(final Score currentScore) {
        List<BowlingState> currentStates = new ArrayList<>(states);
        Score firstScoreCombined = currentStates.get(FIRST_STATE_INDEX)
                .calculateScore(currentScore);
        if (currentScore.hasNumberOfRemainingBonusCount(1)) {
            return firstScoreCombined;
        }
        if (currentScore.hasNumberOfRemainingBonusCount(2) && states.size() == 2) {
            return currentStates.get(SECOND_STATE_INDEX)
                    .calculateScore(firstScoreCombined);
        }
        if (this.isEnd()) {
            return calculateScore();
        }
        throw new NotCountScore("아직 추가 투구 기회가 남았습니다.");
    }
}
