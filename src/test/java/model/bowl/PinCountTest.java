package model.bowl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.params.provider.Arguments.of;

class PinCountTest {

    @ParameterizedTest
    @ValueSource(ints = {10, -1})
    @DisplayName("볼링 핀의 갯수는 최대 10인 양수입니다.")
    void validateSize() {
        //given
        int input = 11;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new PinCount(input))
                .withMessage("볼링 핀의 갯수는 최대 10인 양수입니다.");
    }

    @Test
    @DisplayName("볼링 핀의 갯수를 반환한다.")
    void getValue() {
        //given
        int input = 5;
        PinCount pinCount = new PinCount(input);

        //when
        int actual = pinCount.getValue();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @ParameterizedTest
    @MethodSource("StrikePinParameterProvider")
    @DisplayName("핀의 갯수가 10이면 스트라이크 이다.")
    void isStrike(final int countOfPin, final boolean expect) {
        //given
        PinCount pinCount = new PinCount(countOfPin);

        //when
        boolean actual = pinCount.isStrike();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> StrikePinParameterProvider() {
        return Stream.of(
                of(10, true), of(1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("SparePinParameterProvider")
    @DisplayName("스페어 인 경우 true 반환")
    void isSpare(final PinCount prePinCount, final boolean expect) {
        //given
        PinCount pinCount = new PinCount(3);

        //when
        boolean actual = pinCount.isSpare(prePinCount);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> SparePinParameterProvider() {
        return Stream.of(
                of(new PinCount(7), true), of(new PinCount(2), false)
        );
    }

    @Test
    @DisplayName("핀을 더 쓰러트려 핀 갯수를 더한다")
    void plus() {
        //given
        PinCount firstPinCount = new PinCount(3);
        PinCount secondPinCount = new PinCount(2);
        int expect = 5;

        //when
        int actual = firstPinCount.plus(secondPinCount);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("거터 인 경우 true 를 반환한다.")
    void isGutter() {
        //given
        PinCount pinCount = new PinCount(0);

        //when
        boolean actual = pinCount.isGutter();

        //then
        assertThat(actual).isTrue();
    }
}