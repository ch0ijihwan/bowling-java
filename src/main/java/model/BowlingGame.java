package model;

import model.frame.Frame;
import model.frame.Frames;
import model.pin.PinCount;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(final Player player, final Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public boolean hasNextPitching() {
        return frames.hasNextPitching();
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames.getFrames());
    }

    public void bowl(final PinCount pinCount) {
        frames.bowl(pinCount);
    }

    public String getName() {
        return player.getPlayerName();
    }

    public int getLastFrameIndex() {
        return frames.getLastFrameIndex();
    }
}
