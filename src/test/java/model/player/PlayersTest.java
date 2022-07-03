package model.player;

import model.pin.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @DisplayName("모든 플레이어의 투구가 다 끝나지 않은 경우 true 를 반환한디.")
    @MethodSource("strikeRepeatCountParameterProvider")
    void hasNextPitching(final int repeatCount, final boolean expect) {
        //given
        Players players = new Players(List.of(new Player("ABC"), new Player("QWE")));
        for (Player player : players.getPlayers()) {
            repeatStrikeBowl(player, repeatCount);
        }

        //when
        boolean actual = players.hasNextPitching();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> strikeRepeatCountParameterProvider() {
        return Stream.of(
                Arguments.of(11, true),
                Arguments.of(12, false)
        );
    }

    private void repeatStrikeBowl(final Player player, final int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            player.bowl(new PinCount(10));
        }
    }
}