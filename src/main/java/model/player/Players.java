package model.player;

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

    public boolean hasNextPitching() {
        return players.stream()
                .anyMatch(player -> player.getFrames().hasNextPitching());
    }
}
