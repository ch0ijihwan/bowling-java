package model.state;

import model.frame.score.Score;
import model.pin.PinCount;
import model.state.status.Status;

public interface BowlingState {

    BowlingState bowl(PinCount knockedDownPinCount);

    boolean isEnd();

    String getScoreSymbol();

    Status getStatus();

    Score createScore();

    Score calculateScore(Score currentScore);
}
