package model;

import model.frame.Frames;
import model.pin.PinCount;
import model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayerName() {
        //given
        Player player = new Player("ABC");

        //when
        String actual = player.getPlayerName();

        //then
        assertThat(actual).isEqualTo("ABC");
    }

    @Test
    @DisplayName("Frames 를 반환한다.")
    void getFrames() {
        //given
        Player player = new Player("ABC");

        Frames actual = player.getFrames();

        //then
        assertThat(actual.getFrames()).hasSize(1);
    }

    @Test
    @DisplayName("투구를 실행한다.")
    void bowl() {
        //given
        Player player = new Player("ABC");

        //when
        player.bowl(new PinCount(10));

        //then
        assertThat(player.getFrames().getFrames()).hasSize(2);
    }
}