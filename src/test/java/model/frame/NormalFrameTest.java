package model.frame;

import model.pin.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임은 인덱스가 1이다.")
    void createFirst() {
        //given
        Frame firstFrame = NormalFrame.createFirst();

        //when
        int actual = firstFrame.getIndex();

        //then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("첫번째 투구 후, 투구를 더 할 수 있으면 기존 프레임을 유지한다.")
    void maintainFrame() {
        //given
        Frame firstFrame = NormalFrame.createFirst();

        //when
        Frame actual = firstFrame.bowl(new PinCount(2));


        //then
        assertThat(actual).isSameAs(firstFrame);
    }

    @Test
    @DisplayName("투구를 실행하고 더이상 투구를 하지 못할 경우 다음 인덱스를 가진 프레임을 생성한다.")
    void nextFrame() {
        //given
        Frame firstFrame = NormalFrame.createFirst();

        //when
        Frame nextFrame = firstFrame.bowl(new PinCount(10));

        //then
        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @Test
    @DisplayName("프레임의 인덱스가 9인 경우 다음 프레임은 LastFrame 이다.")
    void createLastFrameWhenIndexIs9() {
        //given
        Frame frame = NormalFrame.createFirst().bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10))
                .bowl(new PinCount(10));

        //when
        Frame nextFrame = frame.bowl(new PinCount(10));

        //then
        assertThat(nextFrame).isInstanceOf(LastFrame.class);
    }

    @Test
    @DisplayName("인덱스를 반환한다.")
    void getIndex() {
        //given
        Frame firstFrame = NormalFrame.createFirst();
        Frame secondFrame = firstFrame.bowl(new PinCount(10));
        Frame thirdFrame = secondFrame.bowl(new PinCount(10));

        //when
        int actual = thirdFrame.getIndex();

        //then
        assertThat(actual).isEqualTo(3);
    }

    @Test
    @DisplayName("점수를 반환받는다.")
    void getScore() {
        //given
        Frame frame = NormalFrame.createFirst()
                .bowl(new PinCount(1));

        int expect = -1;

        //given
        int actual = frame.getScore();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}