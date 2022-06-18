package model.state;

import model.pin.PinCount;

public interface BowlingState {

    BowlingState bowl(PinCount knockedDownPinCount);

    boolean isEnd();

    String getScoreSymbol();
}
