package model.state.running;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.ended.Gutter;
import model.state.ended.Miss;
import model.state.ended.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SecondPitchTest {

    @Test
    @DisplayName("첫번째 투구와 두번째 투구의 핀 갯수는 10을 넘으면 안된다.")
    void validateSecondPitching() {
        //given
        PinCount firstPinCount = new PinCount(3);
        BowlingState runningState = SecondPitch.create(firstPinCount);

        //when
        assertThatIllegalArgumentException().isThrownBy(() -> runningState.bowl(new PinCount(8)))
                .withMessage("첫 번째, 투구와 두번째 투구의 쓰러진 핀의 갯수는 10이 넘으면 안됩니다.");
    }


    @Test
    @DisplayName("두 번째 투구 후, 둘의 합이 10인 경우 스페어를 반환한다.")
    void bowlSpare() {
        //given
        PinCount firstPinCount = new PinCount(0);
        PinCount secondPinCount = new PinCount(10);
        BowlingState runningState = SecondPitch.create(firstPinCount);

        //when
        BowlingState actual = runningState.bowl(secondPinCount);

        //then
        assertThat(actual).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("두 번째 투구 후, 핀들의 갯수가 10보다 작고 0 보다 크면 미스를 반환한다.")
    void bowlMiss() {
        //given
        PinCount firstPinCount = new PinCount(0);
        PinCount secondPinCount = new PinCount(1);
        BowlingState runningState = SecondPitch.create(firstPinCount);

        //when
        BowlingState actual = runningState.bowl(secondPinCount);

        //then
        assertThat(actual).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("두 번째 투구 후, 둘의 합이 0 인경우 거터를 반환한다.")
    void bowlGutter() {
        //given
        PinCount firstPinCount = new PinCount(0);
        PinCount secondPinCount = new PinCount(0);
        BowlingState runningState = SecondPitch.create(firstPinCount);

        //when
        BowlingState actual = runningState.bowl(secondPinCount);

        //then
        assertThat(actual).isInstanceOf(Gutter.class);
    }
}