package model.frame;

import model.frame.score.Score;
import model.pin.PinCount;

public interface Frame {

    Frame bowl(PinCount pinCount);

    int getIndex();

    boolean isEnd();

    String getScoreSymbol();

    int getScore();

    Score addScore(Score score);
}
