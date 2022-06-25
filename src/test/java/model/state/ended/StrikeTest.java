package model.state.ended;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 기호를 반환한다.")
    void getSymbol() {

        //given
        BowlingState strike = Strike.create();
        String expect = "X";

        //when
        String actual = strike.getScoreSymbol();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("스트라이크 인 경우 두번 째 투구가 불가능하다.")
    void bowl() {
        //given
        BowlingState strike = Strike.create();
        PinCount pinCount = new PinCount(1);

        //when
        assertThatIllegalStateException().isThrownBy(() -> strike.bowl(pinCount))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("스트라이크 상태는 투구가 끝난 상태이다.")
    void isEnd() {
        //given
        BowlingState strike = Strike.create();

        //when
        boolean actual = strike.isEnd();
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("보너스 점수 횟수가 횟수가 2번인 경우(스트라이크), 점수를 확인 할 수 없다. - 예외처리")
    void addScoreWhenStrike() {
        //given
        BowlingState strike = Strike.create();
        int expect = 20;
        Score score = strike.calculateScore(Score.createStrikeScore());
        //when
        assertThatThrownBy(score::getScoreValue).isInstanceOf(NotCountScore.class)
                .hasMessage("추가 득점 기회가 남아 있어, 점수를 확인 할 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 점수 횟수가 횟수가 1번인 경우(스페어) 첫번째 점수를 더한 스코어를 반환한다.")
    void addScoreWhenSpare() {
        //given
        BowlingState strike = Strike.create();
        int expect = 20;

        //when
        Score actual = strike.calculateScore(Score.createSpareScore());

        //then
        assertThat(actual.getScoreValue()).isEqualTo(expect);
    }
}