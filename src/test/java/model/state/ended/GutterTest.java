package model.state.ended;

import model.pin.PinCount;
import model.state.BowlingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class GutterTest {

    @Test
    @DisplayName("Gutter 에 해당하는 기호를 반환한다.")
    void getScoreSymbol() {
        //given
        BowlingState gutter = Gutter.create();
        String expect = "- -";

        //when
        String actual = gutter.getScoreSymbol();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("gutter 상태에는 투구를 더 할 수 없다.")
    void bowl() {
        //given
        BowlingState gutter = Gutter.create();

        //when
        assertThatIllegalStateException().isThrownBy(() -> gutter.bowl(new PinCount(1)))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("gutter 는 모든 투구가 끝난 상태이다.")
    void isEnd() {
        //given
        BowlingState gutter = Gutter.create();

        //when
        boolean actual = gutter.isEnd();

        //then
        assertThat(actual).isTrue();
    }
}