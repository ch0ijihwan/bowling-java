package controller;

import model.pin.PinCount;
import model.player.Player;
import model.player.Players;
import view.input.Input;
import view.output.Output;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Controller {
    private final Input input;
    private final Output output;

    public Controller(final Input input, final Output output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        int numberOfPlayer = input.inputNumberOfPlayer();
        List<Player> playerList = IntStream.range(0, numberOfPlayer)
                .mapToObj(number -> new Player(input.inputPlayerName(number)))
                .collect(Collectors.toUnmodifiableList());
        Players players = new Players(playerList);

        output.printCurrentStatus(players);
        while (players.hasNextPitching()) {
            players.getPlayers()
                    .forEach(player -> playFrame(player, players));
        }

    }

    private void playFrame(Player player, Players players) {
        int lastFrameIndex = player.getFrames().getLastFrameIndex();
        while (!player.didBowlingEndAt(lastFrameIndex)) {
            player.bowl(new PinCount(input.inputKnockedDownPinCount(player.getPlayerName())));
            output.printCurrentStatus(players);
        }
    }
}
