package model.player;

import model.frame.Frames;
import model.pin.PinCount;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public Players() {
        players = new ArrayList<>();
    }

    public void addPlayer(final String name) {
        players.add(new Player(name));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Frames getPlayerFrames(final int index) {
        return players.get(index)
                .getFrames();
    }

    public void bowl(final int KnockedDawnPinCount) {
        PinCount pinCount = new PinCount(KnockedDawnPinCount);
        Player bowlTurnPlayer = findNextTurnPlayer();
        bowlTurnPlayer.bowl(pinCount);
    }

    private Player findNextTurnPlayer() {
        return players.stream()
                .filter(player -> getLastFrameIndex(player) == findMinimumLastIndex())
                .findFirst()
                .orElseThrow();
    }

    private int getLastFrameIndex(final Player player) {
        return player.getFrames()
                .getLastFrameIndex();
    }

    private int findMinimumLastIndex() {
        return players.stream()
                .mapToInt(this::getLastFrameIndex)
                .min()
                .orElseThrow();
    }
}
