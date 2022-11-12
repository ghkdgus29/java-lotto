package lotto;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void validationNumberSize() {
        ByteArrayInputStream in = new ByteArrayInputStream("1,3,5,7,9,11,13".getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> new LottoDraw(new LottoBuyer(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 당첨 번호는 6개입니다.");
    }

    @Test
    void numberDuplicateValidation() {
        ByteArrayInputStream in = new ByteArrayInputStream("1,3,5,3,9,11\n12".getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> new LottoDraw(new LottoBuyer(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복될 수 없습니다.");
    }

    @Test
    void numberRangeValidation() {
        ByteArrayInputStream in = new ByteArrayInputStream("1,2,46,3,9,11\n12".getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> new LottoDraw(new LottoBuyer(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 당첨 번호는 1 ~ 45 범위입니다.");
    }

}