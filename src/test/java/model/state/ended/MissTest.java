package model.state.ended;

import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class MissTest {

    private BowlingState miss;

    @BeforeEach
    void setUp() {
        PinCount firstPinCount = new PinCount(3);
        PinCount secondPinCount = new PinCount(5);
        miss = Miss.create(firstPinCount, secondPinCount);
    }

    @Test
    @DisplayName("Miss 상태는 더이상 투구를 할 수 없다.")
    void bowl() {
        //given
        PinCount pinCount = new PinCount(1);

        //when
        assertThatIllegalStateException().isThrownBy(() -> miss.bowl(pinCount))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("미스에 해당하는 핀의 갯수 기호를 반환한다.")
    void getScoreSymbol() {
        //given
        String expect = "3|5";

        //when
        String actual = miss.getScoreSymbol();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("Miss 는 모든 투구가 끝난 상태이다.")
    void isEnd() {
        //when
        boolean actual= miss.isEnd();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("보너스 점수 횟수가 2번인 경우 첫번째 핀 점수를 더하고, 두번째 핀 점수를 더한 스코어를 반환한다.")
    void addScoreWhenStrike() {
        //given
        int expect = 18;

        //when
        Score actual = miss.addScore(Score.createStrikeScore());

        //then
        assertThat(actual.getScoreValue()).isEqualTo(expect);
    }

    @Test
    @DisplayName("보너스 점수 횟수가 횟수가 1번인 경우 첫번째 핀 점수만 더한 스코어를 반환한다.")
    void addScoreWhenSpare() {
        //given
        int expect = 13;

        //when
        Score actual = miss.addScore(Score.createSpareScore());

        //then
        assertThat(actual.getScoreValue()).isEqualTo(expect);
    }
}