package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SpareTest {

    @Test
    @DisplayName("해당하는 핀 갯수와 스페어 기호를 반환한다.")
    void getScoreSymbol() {
        //given
        BowlingState spare = Spare.create(new PinCount(4), new PinCount(6));
        String expect = "4|/";

        //when
        String actual = spare.getScoreSymbol();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("스페어 상태에서는 투구를 할 수 없다.")
    void bowl() {
        //given
        BowlingState spare = Spare.create(new PinCount(4), new PinCount(6));

        //when
        assertThatIllegalStateException().isThrownBy(() -> spare.bowl(new PinCount(1)))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("스페어 상태는 모든 투구가 종료 된 상태이다.")
    void isEnd() {
        //given
        BowlingState spare = Spare.create(new PinCount(4), new PinCount(6));

        //when
        boolean actual = spare.isEnd();

        //then
        assertThat(actual).isTrue();

    }
}