package view.input;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private static final String INPUT_NUMBER_OF_PLAYER_MASSAGE = "How many people?";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어 %d의 이름은(3 english letters)? : ";
    public static final String INPUT_KNOCKED_DOWN_PIN_COUNT_MESSAGE = "%s's turn  :";

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String inputPlayerName(final int playerNumber) {
        System.out.printf(INPUT_PLAYER_NAME_MESSAGE, playerNumber+1);
        SCANNER.nextLine();
        return SCANNER.nextLine()
                .trim();
    }

    @Override
    public int inputNumberOfPlayer() {
        System.out.print(INPUT_NUMBER_OF_PLAYER_MASSAGE);
        return SCANNER.nextInt();
    }


    @Override
    public int inputKnockedDownPinCount(final String playerName) {
        System.out.printf(INPUT_KNOCKED_DOWN_PIN_COUNT_MESSAGE, playerName);
        return SCANNER.nextInt();
    }
}
