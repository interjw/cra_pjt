package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCarTest {

    @Test
    void runProducedCar() {
    }

    @Test
    void testProducedCar() {
    }

    //    private static boolean isValidCheck() {
    //        if(myTestCar.carType instanceof Sedan && myTestCar.breakSystem instanceof Continental) return false;
    //        if(myTestCar.carType instanceof SUV && myTestCar.engine instanceof TOYOTA) return false;
    //        if(myTestCar.carType instanceof Truck && myTestCar.engine instanceof WIA) return false;
    //        if(myTestCar.carType instanceof Truck && myTestCar.breakSystem instanceof Mando) return false;
    //        if(myTestCar.breakSystem instanceof Bosch_B && !(myTestCar.steeringSystem instanceof Bosch_S)) return false;
    //        return true;
    //    }

    @Test
    void 인밸리드체크_세단_콘티넨탈(){
        TestCar.myTestCar = new MyCar();
        TestCar.myTestCar.set(new Sedan());
        TestCar.myTestCar.set(new Continental());
        boolean result = TestCar.isValidCheck();
        assertFalse(result);
    }

    @Test
    void 인밸리드체크_suv_도요타(){
        TestCar.myTestCar = new MyCar();
        TestCar.myTestCar.set(new SUV());
        TestCar.myTestCar.set(new TOYOTA());
        boolean result = TestCar.isValidCheck();
        assertFalse(result);
    }

    @Test
    void 인밸리드체크_Truck_WIA(){
        TestCar.myTestCar = new MyCar();
        TestCar.myTestCar.set(new Truck());
        TestCar.myTestCar.set(new WIA());
        boolean result = TestCar.isValidCheck();
        assertFalse(result);
    }

    @Test
    void 인밸리드체크_Truck_Mando(){
        TestCar.myTestCar = new MyCar();
        TestCar.myTestCar.set(new Truck());
        TestCar.myTestCar.set(new Mando());
        boolean result = TestCar.isValidCheck();
        assertFalse(result);
    }

    @Test
    void 인밸리드체크_Bosch(){
        TestCar.myTestCar = new MyCar();
        TestCar.myTestCar.set(new Bosch_B());
        TestCar.myTestCar.set(new Bosch_S());
        boolean result = TestCar.isValidCheck();
        assertTrue(result);
    }
}