package model.player;

import model.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("플레이어들을 반환한다.")
    void getPlayers() {
        //given
        List<Player> input = List.of(new Player("ABC"), new Player("QWE"));
        Players players = new Players(input);

        //when
        List<Player> actual = players.getPlayers();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @Test
    @DisplayName("플레이어 이름을 추가한다.")
    void addPlayer() {
        //given
        String inputName = "ABC";
        Players players = new Players();
        int expect = 1;

        //when
        players.addPlayer(inputName);

        //then
        assertThat(players.getPlayers()).hasSize(expect);
    }

    @Test
    @DisplayName("해당하는 인덱스에 있는 플레이어의 프레임을 반환한다.")
    void getFrameByPlayer() {
        //given
        Players players = new Players();
        players.addPlayer("ABC");
        players.addPlayer("QWE");

        //when
        Frames actual = players.getPlayerFrames(0);

        //then
        assertThat(actual.getLastFrameIndex()).isEqualTo(1);
    }
}