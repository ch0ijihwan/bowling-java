package view.output;

import model.player.Player;
import model.frame.Frames;

public interface Output {
    void printCurrentStatus(Frames frames, Player player);
}
