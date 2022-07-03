package model.frame;

import model.pin.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    @DisplayName("첫번째 프레임을 생성한다.")
    void createFirst() {
        //given
        List<Frame> expect = List.of(NormalFrame.createFirst());

        //when
        Frames frames = Frames.createFirst();
        int actual = frames.getLastFrameIndex();

        //then
        assertThat(actual).isEqualTo(0);
    }

    @Test
    @DisplayName("투구를 실행한다.(프레임이 안끝난 경우 다음 프레임을 생성하지 않고, 기존 프레임에서 투구를 진행한다.)")
    void bowl() {
        //given
        Frames frames = Frames.createFirst();

        //when
        frames.bowl(new PinCount(1));

        //then
        assertThat(frames.getLastFrameIndex()).isEqualTo(0);
    }

    @Test
    @DisplayName("투구를 실행한다.(스트라이크인 경우 다음 프레임으로 넘어간다.)")
    void bowlWhenStrike() {
        //given
        Frames frames = Frames.createFirst();

        //when
        frames.bowl(new PinCount(10));

        //then
        assertThat(frames.getLastFrameIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("두번의 투구가 끝난경우 다음 프레임으로 진행한다. (스트라이크가 아닌 경우)")
    void bowlWhenNotStrike() {
        //given
        Frames frames = Frames.createFirst();

        //when
        frames.bowl(new PinCount(1));
        frames.bowl(new PinCount(4));

        //then
        assertThat(frames.getLastFrameIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 프레임을 반환한다.")
    void getFrame() {
        //given
        Frames frames = Frames.createFirst();
        frames.bowl(new PinCount(10));
        frames.bowl(new PinCount(10));

        //when
        List<Frame> actual = frames.getFrames();

        //then
        assertThat(actual).hasSize(3);
    }

    @Test
    @DisplayName("다음 투구를 할 수 있는지 확인한다.")
    void hasNextPitching() {
        //given
        Frames frames = Frames.createFirst();
        for (int i = 0; i < 12; i++) {
            frames.bowl(new PinCount(10));
        }

        //when
        boolean actual = frames.hasNextPitching();

        //then
        assertThat(actual).isFalse();
    }
}