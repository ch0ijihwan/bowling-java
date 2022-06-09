package model.pin;

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
                .withMessage("쓰러트린 볼링 핀의 갯수는 10을 넘을 수 없는 양수입니다.");
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
    @MethodSource("pinCountParameterProvider")
    @DisplayName("핀의 갯수가 스트라이크 인 경우 true 를 반환")
    void isStrike(final int input, final boolean expect) {
        //given
        PinCount pinCount = new PinCount(input);

        //when
        boolean actual = pinCount.isStrike();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> pinCountParameterProvider() {
        return Stream.of(
                Arguments.of(10, true),
                Arguments.of(3, false)
        );
    }
}