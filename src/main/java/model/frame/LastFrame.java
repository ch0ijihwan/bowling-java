package model.frame;

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
    private static final int TWO_STRIKE = 2;

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
        if (isDoubleStrike() || lastState.getStatus() == Status.MISS) {
            return true;
        }

        return bowlCount == MINIMUM_BOWL_COUNT && !lastState.isEnd();
    }

    private boolean isDoubleStrike() {
        return states.stream()
                .filter(bowlingState -> bowlingState.getStatus() == Status.STRIKE)
                .count() == TWO_STRIKE;
    }

    @Override
    public String getScoreSymbol() {
        return states.stream()
                .map(BowlingState::getScoreSymbol)
                .collect(Collectors.joining("|"));
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "states=" + states +
                ", bowlCount=" + bowlCount +
                '}';
    }
}
