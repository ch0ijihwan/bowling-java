package model.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PinCountsTest {

    @Test
    @DisplayName("PinCounts 를 반환한다.")
    void getPinCounts() {
        //given
        List<PinCount> input = List.of(new PinCount(1), new PinCount(2));
        PinCounts pinCounts = new PinCounts(input);

        //when
        List<PinCount> actual = pinCounts.getPinCounts();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @Test
    @DisplayName("PinCount 의 갯수를 반환한다.")
    void getSize() {
        //given
        List<PinCount> input = List.of(new PinCount(1), new PinCount(2));
        PinCounts pinCounts = new PinCounts(input);
        int expect = 2;

        //when
        int actual = pinCounts.getSize();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("새로운 투구를 실행한다.")
    void bowl() {
        //given
        List<PinCount> pinCountList = List.of(new PinCount(1));
        PinCounts pinCounts = new PinCounts(pinCountList);
        PinCount input = new PinCount(2);
        List<PinCount> expect = List.of(new PinCount(1), new PinCount(2));
        //when
        PinCounts addPinCounts = pinCounts.add(input);

        //then
        assertThat(addPinCounts).hasFieldOrPropertyWithValue("pinCounts", expect);
    }

    @Test
    @DisplayName("총 점수를 반환한다.")
    void calculatePinCounts() {
        //given
        List<PinCount> pinCountList = List.of(new PinCount(1), new PinCount(8));
        PinCounts pinCounts = new PinCounts(pinCountList);
        int expect = 9;

        //when
        int actual = pinCounts.calculatePinCounts();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("스페어인지 확인한다.")
    @MethodSource("sparePinCountsParameterProvider")
    void isSpare(final PinCounts pinCounts, final boolean expect) {
        //when
        boolean actual = pinCounts.isSpare();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> sparePinCountsParameterProvider() {
        return Stream.of(
                Arguments.of(new PinCounts(List.of(new PinCount(2), new PinCount(8))), true),
                Arguments.of(new PinCounts(List.of(new PinCount(5), new PinCount(4))), false));
    }

    @ParameterizedTest
    @DisplayName("스트라이크 인지 확인한다.")
    @MethodSource("strikePinCountsParameterProvider")
    void isStrike(final PinCounts pinCounts, final boolean expect) {
        //when
        boolean actual = pinCounts.isStrike();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> strikePinCountsParameterProvider() {
        return Stream.of(
                Arguments.of(new PinCounts(List.of(new PinCount(10), new PinCount(0))), true),
                Arguments.of(new PinCounts(List.of(new PinCount(5), new PinCount(4))), false));
    }

    @Test
    @DisplayName("스트라이크 인경우 투구를 할 수 없다.")
    void validateAddPinCount() {
        //given
        PinCounts pinCounts = new PinCounts(List.of(new PinCount(10)));

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> pinCounts.add(new PinCount(1)))
                .withMessage("스트라이크 이후에는 투구를 할 수 없습니다.");
    }
}