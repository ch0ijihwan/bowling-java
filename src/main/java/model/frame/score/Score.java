package model.frame.score;

public class Score {

    private static final int MAXIMUM_ONT_PITCHING_SCORE = 10;
    private static final int CAN_NOT_CALCULATE_STATE = -1;

    private final int score;
    private final int remainingBonusCount;

    private Score(final int score, final int remainingBonusCount) {
        this.score = score;
        this.remainingBonusCount = remainingBonusCount;
    }

    public static Score createCanNotCalculateScore() {
        return new Score(CAN_NOT_CALCULATE_STATE, 0);
    }

    public static Score createStrikeScore() {
        return new Score(MAXIMUM_ONT_PITCHING_SCORE, 2);
    }

    public static Score createSpareScore() {
        return new Score(MAXIMUM_ONT_PITCHING_SCORE, 1);
    }

    public static Score createMissScore(final int score) {
        if (score >= MAXIMUM_ONT_PITCHING_SCORE) {
            throw new IllegalArgumentException("미스 상태의 점수는 10 이상 일 수 없습니다.");
        }

        return new Score(score, 0);
    }

    public Score bowl(final int knockedPinCount) {
        return new Score(this.score + knockedPinCount, remainingBonusCount - 1);
    }

    public int getScoreValue() {
        if (hasRemainingBonusCount()) {
            throw new NotCountScore("추가 득점 기회가 남아 있어, 점수를 확인 할 수 없습니다.");
        }
        return this.score;
    }

    public boolean hasRemainingBonusCount() {
        return remainingBonusCount > 0;
    }
}
