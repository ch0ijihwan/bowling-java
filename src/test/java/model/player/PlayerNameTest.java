package model.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class PlayerNameTest {
    @Test
    @DisplayName("플레이어의 이름을 반환한다.")
    void getPlayerName() {
        //given
        PlayerName playerName = new PlayerName("ABC");

        //when
        String actual = playerName.getName();

        //then
        assertThat(actual).isEqualTo("ABC");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "123", "A"})
    @DisplayName("플레이어의 이름은 3글자이며 대문자로 구성되어있다.")
    void validatePlayerName(final String name) {
        //when
        assertThatIllegalStateException().isThrownBy(() -> new Player(name))
                .withMessage("플레이어의 이름은 알파벳 대문자로 구성된 3자리 이니셜 입니다.");
    }
}