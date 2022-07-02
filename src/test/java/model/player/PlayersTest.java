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
        players.bowl(10);
        //when
        Frames firstPlayerFrame = players.getPlayerFrames(0);
        Frames secondPlayerFrame = players.getPlayerFrames(1);

        //then
        assertThat(firstPlayerFrame.getFrames()).hasSize(2);
        assertThat(secondPlayerFrame.getFrames()).hasSize(1);
    }

    @Test
    @DisplayName("투구를 진행한다.")
    void playerBowl() {
        //given
        Players players = new Players();
        players.addPlayer("ABC");
        players.addPlayer("QWE");
        players.addPlayer("OPI");

        //when
        players.bowl(10);
        players.bowl(1);
        players.bowl(1);
        players.bowl(0);

        //then
        assertThat(players.getPlayerFrames(0).getLastFrameIndex()).isEqualTo(2);
        assertThat(players.getPlayerFrames(1).getLastFrameIndex()).isEqualTo(2);
        assertThat(players.getPlayerFrames(2).getLastFrameIndex()).isEqualTo(1);
    }
}