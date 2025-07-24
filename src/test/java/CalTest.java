import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//;import static org.mockito.Mockito.*;

class CalTest {

    @Test
    @DisplayName("Mockito 샘플")
    void getSum() {
        Cal cal = mock(Cal.class);
        when(cal.getSum(10,20)).thenReturn(999);

        System.out.println(cal.getSum(10,20));
    }
}