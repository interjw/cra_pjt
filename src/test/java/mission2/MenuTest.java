package mission2;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MenuTest {

    @Test
    void 메뉴테스트1() {
        assertDoesNotThrow(() -> Menu.showCarTypeMenu());
    }

    @Test
    void 메뉴테스트2() {
        assertDoesNotThrow(() -> Menu.showBrakeMenu());
    }

    @Test
    void 메뉴테스트3() {
        assertDoesNotThrow(() -> Menu.showEngineMenu());
    }

    @Test
    void 메뉴테스트4() {
        assertDoesNotThrow(() -> Menu.showSteeringMenu());
    }

    @Test
    void 메뉴테스트5() {
        assertDoesNotThrow(() -> Menu.showRunTestMenu());
    }

}