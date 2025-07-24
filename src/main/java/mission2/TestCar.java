package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.*;

public class TestCar {

    public static final int PRE_TEST_DELAY_MS = 1500;
    static MyCar myTestCar;

    TestCar(MyCar my){
        myTestCar = my;
    }

    public static boolean isValidCheck() {
        if(myTestCar.carType instanceof Sedan && myTestCar.breakSystem instanceof Continental) return false;
        if(myTestCar.carType instanceof SUV && myTestCar.engine instanceof TOYOTA) return false;
        if(myTestCar.carType instanceof Truck && myTestCar.engine instanceof WIA) return false;
        if(myTestCar.carType instanceof Truck && myTestCar.breakSystem instanceof Mando) return false;
        if(myTestCar.breakSystem instanceof Bosch_B && !(myTestCar.steeringSystem instanceof Bosch_S)) return false;
        return true;
    }

    static void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (myTestCar.engine instanceof Broken) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        printRunResult();
    }

    private static void printRunResult() {
        System.out.printf("Car Type : %s\n", myTestCar.carType.getName());
        System.out.printf("Engine   : %s\n", myTestCar.engine.getName());
        System.out.printf("Brake    : %s\n", myTestCar.breakSystem.getName());
        System.out.printf("Steering : %s\n", myTestCar.steeringSystem.getName());
        System.out.println("자동차가 동작됩니다.");
    }

    public static void testProducedCar() {
        System.out.println("Test...");
        delay(PRE_TEST_DELAY_MS);
        if(myTestCar.carType instanceof Sedan && myTestCar.breakSystem instanceof Continental) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if(myTestCar.carType instanceof SUV && myTestCar.engine instanceof TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if(myTestCar.carType instanceof Truck && myTestCar.engine instanceof WIA)  {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if(myTestCar.carType instanceof Truck && myTestCar.breakSystem instanceof Mando) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if(myTestCar.breakSystem instanceof Bosch_B && !(myTestCar.steeringSystem instanceof Bosch_S)) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
