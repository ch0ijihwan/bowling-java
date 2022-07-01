package model;

import model.frame.Frame;
import model.frame.Frames;
import model.pin.PinCount;
import model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private final Player player = new Player("ABC");

    @Test
    @DisplayName("다음 투구를 할 수 있을 경우 true 를 반환한다.")
    void hasNextPitching() {
        //given
        Frames elevenStrikeFrames = generateStrikeFrames(11);
        Frames twelveStrikeFrames = generateStrikeFrames(12);

        BowlingGame bowlingGame1 = new BowlingGame(player, elevenStrikeFrames);
        BowlingGame bowlingGame2 = new BowlingGame(new Player("QWE"), twelveStrikeFrames);

        //when
        boolean twelveStrikeActual = bowlingGame1.hasNextPitching();
        boolean thirteenStrikeActual = bowlingGame2.hasNextPitching();

        //then
        assertThat(thirteenStrikeActual).isFalse();
        assertThat(twelveStrikeActual).isTrue();
    }

    private Frames generateStrikeFrames(final int repeatCount) {
        Frames frames = Frames.createFirst();
        for (int i = 0; i < repeatCount; i++) {
            frames.bowl(new PinCount(10));
        }
        return frames;
    }

    @Test
    @DisplayName("Frames 를 반환한다.")
    void getFrames() {
        //given
        Frames frames = Frames.createFirst();
        BowlingGame bowlingGame = new BowlingGame(player, frames);

        //when
        List<Frame> actual = bowlingGame.getFrames();

        //then
        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("투구를 실행한다.")
    void bowl() {
        //given
        Frames frames = Frames.createFirst();
        BowlingGame bowlingGame = new BowlingGame(player, frames);

        //when
        bowlingGame.bowl(new PinCount(10));

        //then
        assertThat(bowlingGame.getFrames()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getName() {
        //given
        Frames frames = Frames.createFirst();
        BowlingGame bowlingGame = new BowlingGame(player, frames);
        String expect = "ABC";

        //when
        String actual = bowlingGame.getName();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("마지막 프레임 인덱스를 반환한다.")
    void getLastFrameIndex() {
        //given
        Frames frames = Frames.createFirst();
        BowlingGame bowlingGame = new BowlingGame(player, frames);
        bowlingGame.bowl(new PinCount(10));
        bowlingGame.bowl(new PinCount(10));
        int expect = 3;

        //when
        int actual =  bowlingGame.getLastFrameIndex();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}