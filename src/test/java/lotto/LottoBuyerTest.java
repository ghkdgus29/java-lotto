package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoBuyerTest {

    @Test
    void getCost() {
        ByteArrayInputStream in = new ByteArrayInputStream("12000".getBytes());
        System.setIn(in);

        UI.setCost();
        LottoBuyer lottoBuyer = new LottoBuyer(UI.getCost());
        int cost = lottoBuyer.getCost();

        assertThat(cost).isEqualTo(12000);
    }

    @Test
    void costValidation() {
        ByteArrayInputStream in = new ByteArrayInputStream("12100".getBytes());
        System.setIn(in);

        UI.setCost();
        assertThatThrownBy(() -> new LottoBuyer(UI.getCost()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구입 금액은 1,000원 단위로 입력 가능합니다.");

    }
}