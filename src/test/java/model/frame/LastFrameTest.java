package model.frame;

import model.frame.score.Score;
import model.pin.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class LastFrameTest {

    @Test
    @DisplayName("스트라이크 투구 시 한번 더 투구할 수 있다. 총 3번")
    void bowlStrike() {
        //when
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(10))
                .bowl(new PinCount(10));
        Frame actual = lastFrame.bowl(new PinCount(10));

        //then
        assertThat(actual.getIndex()).isEqualTo(10);
        assertThat(actual.isEnd()).isTrue();
        assertThat(actual.getScoreSymbol()).isEqualTo("X|X|X");
    }

    @Test
    @DisplayName("스트라이크 투구 시 한번 더 투구할 수 있다. 총 3번")
    void bowlStrike2() {
        //when
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(10))
                .bowl(new PinCount(10));
        Frame actual = lastFrame.bowl(new PinCount(10));

        //then
        assertThat(actual.getIndex()).isEqualTo(10);
        assertThat(actual.isEnd()).isTrue();
        assertThat(actual.getScoreSymbol()).isEqualTo("X|X|X");
    }

    @Test
    @DisplayName("스페어 투구 시 한번 더 투구 할 수 있다. 총 3번")
    void bowlSpare() {

        //when
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(2))
                .bowl(new PinCount(8));
        Frame actual = lastFrame.bowl(new PinCount(3));

        //then
        assertThat(actual.getIndex()).isEqualTo(10);
        assertThat(actual.isEnd()).isTrue();
        assertThat(actual.getScoreSymbol()).isEqualTo("2|/|3");
    }

    @Test
    @DisplayName("Miss 인 경우 추가 투구를 할 수 없다. 총 2번")
    void bowlMiss() {
        //given
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(2))
                .bowl(new PinCount(2));

        //when
        assertThatIllegalStateException().isThrownBy(() -> lastFrame.bowl(new PinCount(1)))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("Gutter 인 경우 추가 투구를 할 수 없다. 총 2번")
    void bowlGutter() {
        //given
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(0))
                .bowl(new PinCount(0));

        //when
        assertThatIllegalStateException().isThrownBy(() -> lastFrame.bowl(new PinCount(1)))
                .withMessage("이미 투구가 끝난 상태 입니다.");
    }

    @Test
    @DisplayName("인덱스를 반환한다.")
    void getIndex() {
        //given
        Frame lastFrame = LastFrame.create();

        //when
        int actual = lastFrame.getIndex();

        //then
        assertThat(actual).isEqualTo(10);
    }

    @ParameterizedTest
    @MethodSource("allBowlingEndedCasesParameterCreateProvider")
    @DisplayName("모든 투구들이 끝났는지 확인한다.")
    void isEnd(final Frame endedFrame) {

        //when
        boolean actual = endedFrame.isEnd();

        //then
        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> allBowlingEndedCasesParameterCreateProvider() {
        return Stream.of(
                Arguments.of(LastFrame.create().bowl(new PinCount(10)).bowl(new PinCount(10)).bowl(new PinCount(10))),
                Arguments.of(LastFrame.create().bowl(new PinCount(5)).bowl(new PinCount(5)).bowl(new PinCount(1))),
                Arguments.of(LastFrame.create().bowl(new PinCount(5)).bowl(new PinCount(5)).bowl(new PinCount(10))),
                Arguments.of(LastFrame.create().bowl(new PinCount(1)).bowl(new PinCount(2))),
                Arguments.of(LastFrame.create().bowl(new PinCount(0)).bowl(new PinCount(0)))
        );
    }

    @Test
    @DisplayName("점수를 반환받는다.")
    void calculateScore() {
        Frame lastFrame = LastFrame.create().bowl(new PinCount(10)).bowl(new PinCount(10)).bowl(new PinCount(10));
        int expect = 30;

        //when
        int actual = lastFrame.getScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("점수를 받아서, 합친 후 반환한다. - 받은 스코어가 추가 득점 기회가 있는 경우")
    @MethodSource("currentScoreParameterProvider")
    void addScoreWhenStrike(final Score currentScore, final int expect) {
        //given
        Frame lastFrame = LastFrame.create()
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10));

        //when
        Score actual = lastFrame.addScore(currentScore);

        //then
        assertThat(actual.getScoreValue()).isEqualTo(expect);
    }

    private static Stream<Arguments> currentScoreParameterProvider() {
        return Stream.of(
                Arguments.of(Score.createStrikeScore(), 30),
                Arguments.of(Score.createSpareScore(), 20)
        );
    }
}

