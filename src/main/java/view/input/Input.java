package view.input;

public interface Input {

    int inputNumberOfPlayer();
    String inputPlayerName(int playerNumber);

    int inputKnockedDownPinCount(String playerName);
}
