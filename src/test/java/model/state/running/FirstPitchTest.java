package model.state.running;

import model.pin.PinCount;
import model.state.BowlingState;
import model.state.ended.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FirstPitchTest {

    @ParameterizedTest
    @MethodSource("firstPinCountParameterProvider")
    @DisplayName("핀을 입력받아, 해당하는 상태를 반환한다.")
    void bowl(final PinCount firstPinCount, final Class<?> expect) {

        //given
        RunningState runningState = FirstPitch.create();

        //when
        BowlingState actual = runningState.bowl(firstPinCount);

        //then
        assertThat(actual).isInstanceOf(expect);
    }

    private static Stream<Arguments> firstPinCountParameterProvider() {
        return Stream.of(
                Arguments.of(new PinCount(10), Strike.class),
                Arguments.of(new PinCount(0), SecondPitch.class),
                Arguments.of(new PinCount(1), SecondPitch.class)
        );
    }
}