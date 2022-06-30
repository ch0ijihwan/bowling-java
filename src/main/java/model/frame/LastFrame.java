package model.frame;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.running.FirstPitch;
import model.state.status.Status;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

    private static final int MAXIMUM_BOWL_COUNT = 3;
    private static final int MINIMUM_BOWL_COUNT = 2;
    private static final int LAST_FRAME_INDEX = 10;
    private static final String DELIMITER = "|";

    private final Deque<BowlingState> states = new ArrayDeque<>();
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
        BowlingState current = states.getLast();

        if (isStrikeOrSpare(current)) {
            bonusBowl(pinCount);
            return this;
        }

        states.removeLast();
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

        return bowlCount == MINIMUM_BOWL_COUNT && states.getLast().getStatus() == Status.MISS;
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
        Deque<BowlingState> currentStates  = new ArrayDeque<>(states);
        Score score = currentStates.removeFirst()
                .createScore();
        while (!currentStates.isEmpty()){
            score = currentStates.removeFirst()
                    .calculateScore(score);
        }
        return score;
    }

    @Override
    public Score addScore(final Score currentScore) {
        if (states.getLast().isEnd()) {
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
