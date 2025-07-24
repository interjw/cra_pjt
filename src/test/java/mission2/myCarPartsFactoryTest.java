package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class myCarPartsFactoryTest {
    private myCarPartsFactory factory;

    @BeforeEach
    public void setup() {
        factory = new myCarPartsFactory();
    }

    @Test
    public void 테스트타입() {
        assertTrue(factory.createCarType(1) instanceof Sedan);
        assertTrue(factory.createCarType(2) instanceof SUV);
        assertTrue(factory.createCarType(3) instanceof Truck);
        assertNull(factory.createCarType(99)); // 없는 파트 번호
    }

    @Test
    public void 테스트엔진() {
        assertTrue(factory.createEngine(1) instanceof GM);
        assertTrue(factory.createEngine(2) instanceof TOYOTA);
        assertTrue(factory.createEngine(3) instanceof WIA);
        assertTrue(factory.createEngine(4) instanceof Broken);
        assertNull(factory.createEngine(99));
    }

    @Test
    public void 테스트브레이크() {
        assertTrue(factory.createBrake(1) instanceof Mando);
        assertTrue(factory.createBrake(2) instanceof Continental);
        assertTrue(factory.createBrake(3) instanceof Bosch_B);
        assertNull(factory.createBrake(99));
    }

    @Test
    public void 테스트스티어링() {
        assertTrue(factory.createSteering(1) instanceof Bosch_S);
        assertTrue(factory.createSteering(2) instanceof Mobis);
        assertNull(factory.createSteering(99));
    }
}