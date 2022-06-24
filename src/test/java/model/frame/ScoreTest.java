package model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ScoreTest {

    @Test
    @DisplayName("투구 실행 시, 투구 실행 후, 점수를 더한 새로운 Score 객체를 생성해 반환한다. 이때, 보너스 기회가 있을 경우 보너스 기회를 하나 줄인다. ")
    void bowl() {
        //given
        Score oneStrikeScore = new Score(10, 2);

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
        Score score = new Score(10, 2).bowl(5).bowl(1);
        int expect =  16;
        
        //when
        int actual = score.getScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }
    
    @Test
    @DisplayName("보너스 점수 기회 횟수가 남아 있는 경우, 점수를 확인 할 수 없다.")
    void validateRemainingBonusCount() {
        //given
        Score score = new Score(10, 2).bowl(5);

        //when
        assertThatIllegalStateException().isThrownBy(() -> score.getScore())
                .withMessage("추가 득점 기회가 남아 있어, 점수를 확인 할 수 없습니다.");
    }
}