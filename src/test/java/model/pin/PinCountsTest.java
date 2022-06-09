package model.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        pinCounts.add(input);

        //then
        assertThat(pinCounts).hasFieldOrPropertyWithValue("pinCounts", expect);
    }

    @Test
    @DisplayName("총 점수를 반환한다.")
    void calculatePinCounts() {
        //given
        List<PinCount> pinCountList = List.of(new PinCount(1), new PinCount(8));
        PinCounts pinCounts = new PinCounts(pinCountList);
        int expect = 3;

        //when
        int actual = pinCounts.calculatePinCounts();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}