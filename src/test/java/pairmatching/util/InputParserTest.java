package pairmatching.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

    @DisplayName("존재하지 않는 미션인 경우 예외가 발생한다.")
    @Test
    void pairTypeParser1() {
        // given
        String input = "백엔드, 레벨1, 오류";

        // when // then
        assertThatThrownBy(() -> InputParser.pairTypeParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("미션이 존재하지 않는 레벨인 경우 예외가 발생한다.")
    @Test
    void pairTypeParser2() {
        // given
        String input = "백엔드, 레벨3, 자동차경주";

        // when // then
        assertThatThrownBy(() -> InputParser.pairTypeParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}