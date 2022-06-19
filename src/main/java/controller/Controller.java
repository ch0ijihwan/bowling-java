package controller;

import model.Player;
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

    public void run(){
        Player player = new Player(input.inputPlayerName());
        Frames frames = Frames.createFirst();
        while (frames.hasNextPitching()){
            int knockedDownPinCountValue= input.inputKnockedDownPinCount(frames.getLastFrameIndex());
            PinCount knockedPinCount = new PinCount(knockedDownPinCountValue);
            frames.bowl(knockedPinCount);
            output.printCurrentStatus(frames, player);
        }
    }
}
