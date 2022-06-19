package view.output;

import model.Player;
import model.frame.Frames;

import java.util.stream.Collectors;

public class ConsoleOutput implements Output {

    private static final String BOWLING_SCORE_HEAD_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME_FORMAT = "|  %s |";
    private static final String EMPTY_FRAME_FORMAT = "      |";
    private static final String ONE_SPACE_FORMAT = " %-5.4s|";
    private static final int ALL_FRAME_COUNT = 10;

    @Override
    public void printCurrentStatus(final Frames frames, final Player player) {
        System.out.println(BOWLING_SCORE_HEAD_FORMAT);
        String currentStatus = getCurrentStatus(frames);
        System.out.printf(createPlayerNameFormat(player) + currentStatus + createEmptyFrameFormat(countEmptyFrame(frames)));
    }

    private String getCurrentStatus(final Frames frames) {
        return frames.getFrames()
                .stream()
                .map(frame -> addBlank(frame.getScoreSymbol()))
                .collect(Collectors.joining());
    }

    private String addBlank(final String symbol) {
        return String.format(ONE_SPACE_FORMAT, symbol);
    }

    private String createPlayerNameFormat(final Player player) {
        return String.format(PLAYER_NAME_FORMAT, player.getPlayerName());
    }

    private int countEmptyFrame(final Frames frames) {
        return ALL_FRAME_COUNT - frames.getLastFrameIndex();
    }

    private String createEmptyFrameFormat(final int emptyFrameCount) {
        return EMPTY_FRAME_FORMAT.repeat(emptyFrameCount) + "\n";
    }
}
