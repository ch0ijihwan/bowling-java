package model.frame;

import model.pin.PinCount;

public interface Frame {

    Frame bowl(PinCount pinCount);

    int getIndex();

    boolean isEnd();

    String getScoreSymbol();
}
