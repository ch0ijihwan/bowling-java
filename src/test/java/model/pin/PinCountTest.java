package model.pin;

import model.frame.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinCountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("볼링핀의 갯수는 10을 넘을 수 없는 양수이다.")
    void validateSize(final int input) {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new PinCount(input))
                .withMessage("쓰러트린 볼링 핀의 갯수는 10 이하의 양수입니다.");
    }

    @Test
    @DisplayName("핀의 갯수를 반환한다.")
    void getValue() {
        //given
        int input = 3;
        PinCount pinCount = new PinCount(input);

        //when
        int actual = pinCount.getValue();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @ParameterizedTest
    @MethodSource("StrikePinCountParameterProvider")
    @DisplayName("핀의 갯수가 스트라이크 인 경우 true 를 반환")
    void isStrike(final int input, final boolean expect) {
        //given
        PinCount pinCount = new PinCount(input);

        //when
        boolean actual = pinCount.isStrike();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> StrikePinCountParameterProvider() {
        return Stream.of(
                Arguments.of(10, true),
                Arguments.of(3, false)
        );
    }

    @ParameterizedTest
    @MethodSource("sparePinCountParameterProvider")
    @DisplayName("핀의 갯수가 스페어 인 경우 true 반환")
    void isSpare(final PinCount secondPinCount, final boolean expect) {
        //given
        PinCount firstPinCount = new PinCount(4);

        //when
        boolean actual = firstPinCount.isSpare(secondPinCount);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> sparePinCountParameterProvider() {
        return Stream.of(
                Arguments.of(new PinCount(6), true),
                Arguments.of(new PinCount(3), false)
        );
    }

    @Test
    @DisplayName("스코어를 받아, 현재 핀 갯수를 더한 스코어를 만들어 반환한다.")
    void sunScore() {
        //given
        PinCount pinCount = new PinCount(2);

        //when
        Score actual = pinCount.sumScore(Score.createMissScore(3));

        //then
        assertThat(actual).hasFieldOrPropertyWithValue("score", 5);
    }
}