package model.player;

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
}