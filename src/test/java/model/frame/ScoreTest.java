package model.frame;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("투구 실행 시, 투구 실행 후, 점수를 더한 새로운 Score 객체를 생성해 반환한다. 이때, 보너스 기회가 있을 경우 보너스 기회를 하나 줄인다. ")
    void bowl() {
        //given
        Score oneStrikeScore = Score.createStrikeScore();

        //when
        Score actual = oneStrikeScore.bowl(3);

        //then
        assertThat(actual).hasFieldOrPropertyWithValue("score", 13)
                .hasFieldOrPropertyWithValue("remainingBonusCount", 1);
    }

    @Test
    @DisplayName("점수를 반환한다.")
    void getScore() {
        //given
        Score score = Score.createStrikeScore().bowl(5).bowl(1);
        int expect = 16;

        //when
        int actual = score.getScoreValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("보너스 점수 기회 횟수가 남아 있는 경우, 점수를 확인 할 수 없다.")
    void validateRemainingBonusCount() {
        //given
        Score score = Score.createStrikeScore().bowl(5);

        //when
        assertThatThrownBy(score::getScoreValue).isInstanceOf(NotCountScore.class)
                .hasMessage("추가 득점 기회가 남아 있어, 점수를 확인 할 수 없습니다.");
    }

    @Test
    @DisplayName("스트라이크 점수를 생성한다.")
    void createStrikeScore() {
        //given
        int expectScore = 10;
        int expectRemainingBonusCount = 2;

        //when
        Score score = Score.createStrikeScore();

        //then
        assertThat(score).hasFieldOrPropertyWithValue("score", expectScore)
                .hasFieldOrPropertyWithValue("remainingBonusCount", expectRemainingBonusCount);
    }

    @Test
    @DisplayName("스페어 점수를 생성한다.")
    void createSpareScore() {
        //given
        int expectScore = 10;
        int expectRemainingBonusCount = 1;

        //when
        Score score = Score.createSpareScore();

        //then
        assertThat(score).hasFieldOrPropertyWithValue("score", expectScore)
                .hasFieldOrPropertyWithValue("remainingBonusCount", expectRemainingBonusCount);
    }

    @Test
    @DisplayName("미스 상태의 점수를 생성한다.")
    void createMissScore() {
        //given
        int input = 9;
        int expectScore = 9;
        int expectRemainingBonusCount = 0;

        //when
        Score score = Score.createMissScore(input);

        //then
        assertThat(score).hasFieldOrPropertyWithValue("score", expectScore)
                .hasFieldOrPropertyWithValue("remainingBonusCount", expectRemainingBonusCount);
    }

    @Test
    @DisplayName("미스 상태의 점수는 10 이상 일 수 없다.")
    void validateMissScore() {
        //given
        int input = 10;

        assertThatIllegalArgumentException().isThrownBy(() -> Score.createMissScore(input))
                .withMessage("미스 상태의 점수는 10 이상 일 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("입력 받은 보너스 점수 기회가 현재 가지고 있는 기회와 일치하면 true를 반환한다.")
    @MethodSource("scoreParameterProvider")
    void hasNumberOfRemainingBonusCount(final Score score, final int input) {
        //when
        boolean actual = score.hasNumberOfRemainingBonusCount(input);

        //then
        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> scoreParameterProvider() {
        return Stream.of(
                Arguments.of(Score.createStrikeScore(), 2),
                Arguments.of(Score.createSpareScore(), 1),
                Arguments.of(Score.createMissScore(1), 0));
    }
}