package view.input;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "플레이어의 이름은(3 english letters)? : ";
    public static final String INPUT_KNOCKED_DOWN_PIN_COUNT_MESSAGE = "%d 트레임 투구 :";

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String inputPlayerName() {
        System.out.print(INPUT_PLAYER_NAME_MESSAGE);
        return SCANNER.nextLine()
                .trim();
    }

    @Override
    public int inputKnockedDownPinCount(final int currentFrameIndex) {
        System.out.printf(INPUT_KNOCKED_DOWN_PIN_COUNT_MESSAGE, currentFrameIndex);
        return SCANNER.nextInt();
    }
}
