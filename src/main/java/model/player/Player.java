package model.player;

import model.frame.Frame;
import model.frame.Frames;
import model.pin.PinCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private final PlayerName name;
    private final Frames frames;

    public Player(final String name) {
        this.name = new PlayerName(name);
        this.frames = Frames.createFirst();
    }

    public String getPlayerName() {
        return name.getName();
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames.getFrames());
    }

    public void bowl(final PinCount pinCount) {
        frames.bowl(pinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
