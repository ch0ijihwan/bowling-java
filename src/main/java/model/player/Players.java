package model.player;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean hasNextPitching() {
        return players.stream()
                .anyMatch(player -> player.getFrames().hasNextPitching());
    }
}
