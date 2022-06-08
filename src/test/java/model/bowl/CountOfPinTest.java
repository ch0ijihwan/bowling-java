package model.bowl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CountOfPinTest {

    @ParameterizedTest
    @ValueSource(ints = {10, -1})
    @DisplayName("볼링 핀의 갯수는 최대 10인 양수입니다.")
    void validateSize() {
        //given
        int input = 11;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new CountOfPin(input))
                .withMessage("볼링 핀의 갯수는 최대 10인 양수입니다.");
    }


    @Test
    @DisplayName("볼링 핀의 갯수를 반환한다.")
    void getValue() {
        //given
        int input = 5;
        CountOfPin countOfPin = new CountOfPin(input);

        //when
        int actual = countOfPin.getValue();

        //then
        assertThat(actual).isEqualTo(input);
    }
}