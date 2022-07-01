package view.output;

import model.player.Player;
import model.frame.Frame;
import model.frame.Frames;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleOutput implements Output {

    private static final String BOWLING_SCORE_HEAD_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME_FORMAT = "|  %s |";
    private static final String EMPTY_FRAME_FORMAT = "      |";
    private static final String ONE_SPACE_FORMAT = "%6s|";
    private static final String LEFT_EMPTY_FORMAT = "|      |";
    private static final int ALL_FRAME_COUNT = 10;
    private static final int CAN_NOT_CALCULATE_STATE = -1;

    @Override
    public void printCurrentStatus(final Frames frames, final Player player) {
        System.out.println(BOWLING_SCORE_HEAD_FORMAT);
        String currentStatus = getCurrentStatus(frames);
        System.out.printf(createPlayerNameFormat(player) + currentStatus + createEmptyFrameFormat(countEmptyFrame(frames)));
        System.out.println(createTotalScoreFormat(frames));
    }


    private String getCurrentStatus(final Frames frames) {
        return frames.getFrames()
                .stream()
                .map(frame -> addBlank(frame.getScoreSymbol()))
                .collect(Collectors.joining(""));
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

    private String createTotalScoreFormat(final Frames frames) {
        return String.format("%s%s%s", LEFT_EMPTY_FORMAT, createAllScoreFormat(frames), createEmptyScoreFormat(frames));
    }

    private String createEmptyScoreFormat(final Frames frames) {
        return IntStream.range(0, countEmptyScoreSpace(frames))
                .mapToObj(index -> EMPTY_FRAME_FORMAT)
                .collect(Collectors.joining());
    }

    private int countEmptyScoreSpace(final Frames frames) {
        return 10 - frames.getFrames()
                .size();
    }

    private String createAllScoreFormat(final Frames frames) {
        return frames.getFrames()
                .stream()
                .map(frame -> changeScoreFormat(frames, frame))
                .collect(Collectors.joining());
    }

    private String changeScoreFormat(final Frames frames, final Frame frame) {
        if (frame.getScore() == CAN_NOT_CALCULATE_STATE) {
            return EMPTY_FRAME_FORMAT;
        }
        return String.format(ONE_SPACE_FORMAT, totalScore(frames, frames.getFrames().indexOf(frame) + 1));
    }

    private int totalScore(final Frames frames, final int index) {
        return frames.getFrames()
                .stream()
                .limit(index)
                .mapToInt(Frame::getScore)
                .sum();
    }
}
