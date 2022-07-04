package model.frame;

import model.pin.PinCount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public static Frames createFirst() {
        return new Frames();
    }

    private Frames() {
        this.frames.add(NormalFrame.createFirst());
    }

    public int getLastFrameIndex() {
        return getLastPositionFrame().getIndex();
    }

    private Frame getLastPositionFrame() {
        return frames.get(getLastIndex());
    }

    private int getLastIndex() {
        return frames.size() - 1;
    }

    public void bowl(final PinCount pinCount) {
        Frame currentFrame = getLastPositionFrame();
        Frame resultFrame = currentFrame.bowl(pinCount);
        if (currentFrame.isEnd() && !resultFrame.isEnd()) {
            frames.add(resultFrame);
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean hasNextPitching() {
        return !(getLastPositionFrame().isEnd() && frames.size() == 10);
    }
}
