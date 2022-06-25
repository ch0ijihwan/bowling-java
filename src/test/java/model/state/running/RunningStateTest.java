package model.state.running;

import model.frame.score.NotCountScore;
import model.frame.score.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RunningStateTest {
    private BowlingState runningState;

    @BeforeEach
    void setUp() {
        runningState = new RunningState() {
            @Override
            public BowlingState bowl(final PinCount knockedDownPinCount) {
                throw new IllegalStateException("테스트 코드 중 해당 메서드를 사용하지 않습니다.");
            }

            @Override
            public String getScoreSymbol() {
                throw new IllegalStateException("테스트 코드 중 해당 메서드를 사용하지 않습니다.");
            }

            @Override
            public Score addScore(final Score currentScore) {
                throw  new IllegalStateException("테스트 코드 중 해당 메서드를 사용하지 않습니다.");
            }
        };
    }

    @Test
    @DisplayName("투구 실행 중인 상태는 모든 투구가 끝난 상태가 아니다.")
    void isEnd() {
        //when
        boolean actual = runningState.isEnd();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("투구 실행 도중에는 점수를 확인 할 수 없다.")
    void getScore() {
        //when
        assertThatThrownBy(runningState::getScore).isInstanceOf(NotCountScore.class)
                .hasMessage("투구 실행 중에는 점수를 확인 할 수 없습니다.");
    }
}