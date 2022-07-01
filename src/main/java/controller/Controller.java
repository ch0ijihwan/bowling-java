package controller;

import model.BowlingGame;
import model.player.Player;
import model.frame.Frames;
import model.pin.PinCount;
import view.input.Input;
import view.output.Output;

public class Controller {
    private final Input input;
    private final Output output;

    public Controller(final Input input, final Output output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        Player player = new Player(input.inputPlayerName());
        Frames frames = Frames.createFirst();
        BowlingGame bowlingGame = new BowlingGame(player, frames);
        while (bowlingGame.hasNextPitching()) {
            PinCount knockedPinCount = inputKnockedPinCount(frames);
            bowlingGame.bowl(knockedPinCount);
            output.printCurrentStatus(frames, player);
        }
    }

    private PinCount inputKnockedPinCount(final Frames frames) {
        int pinCount = input.inputKnockedDownPinCount(frames.getLastFrameIndex());
        return new PinCount(pinCount);
    }
}
