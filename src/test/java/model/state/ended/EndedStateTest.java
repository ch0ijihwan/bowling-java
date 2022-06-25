package model.state.ended;

import model.frame.Score;
import model.pin.PinCount;
import model.state.BowlingState;
import model.state.running.SecondPitch;
import model.state.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EndedStateTest {

    private final BowlingState endedState = new EndedState() {
        @Override
        public String getScoreSymbol() {
            return "testEndedState";
        }

        @Override
        public Status getStatus() {
            return Status.MISS;
        }

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public Score addScore(Score currentScore) {
            return null;
        }
    };

    @Test
    @DisplayName("모든 투구가 끝났음을 알린다.")
    void isEnd() {

        //when
        boolean actual = endedState.isEnd();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("EndedState 인 경우 더이상 투구를 할 수 없다.")
    void bowl() {
        //given
        PinCount pinCount = new PinCount(3);

        //when
        assertThatIllegalStateException().isThrownBy(() -> endedState.bowl(pinCount))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("두번째 투구를 할 경우, 쓰러트린 모든 핀의 갯수가 10이 넘으면 예외처리한다.")
    void validateSecondPitching() {
        //given
        BowlingState bowlingState = SecondPitch.create(new PinCount(7));
        PinCount secondPinCount = new PinCount(4);

        //when
        assertThatIllegalArgumentException().isThrownBy(() -> bowlingState.bowl(secondPinCount))
                .withMessage("첫 번째, 투구와 두번째 투구의 쓰러진 핀의 갯수는 10이 넘으면 안됩니다.");
    }
}