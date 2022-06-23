package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

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
}