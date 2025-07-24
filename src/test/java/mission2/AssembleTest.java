package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AssembleTest {

    MyCar car = new MyCar();
    @Test
    public void car_네이밍테스트() {
        car.set(new Sedan());
        String expectedName = "Sedan";
        assertEquals(expectedName, car.carType.getName());
    }

    @Test
    public void car_네이밍테스트2() {
        car.set(new SUV());
        String expectedName = "SUV";
        assertEquals(expectedName, car.carType.getName());
    }

    @Test
    public void car_네이밍테스트3() {
        car.set(new Truck());
        String expectedName = "Truck";
        assertEquals(expectedName, car.carType.getName());
    }

    @Test
    public void bosh_Steering_네이밍테스트() {
        car.set(new Bosch_S());
        String expectedName = "BOSCH";
        assertEquals(expectedName, car.steeringSystem.getName());
    }

    @Test
    public void bosch_Brake_네이밍테스트() {
        car.set(new Bosch_B());
        String expectedName = "BOSCH";
        assertEquals(expectedName, car.breakSystem.getName());
    }

    @Test
    public void Mobis_Steering_네이밍테스트() {
        car.set(new Mobis());
        String expectedName = "Mobis";
        assertEquals(expectedName, car.steeringSystem.getName());
    }


    @Test
    public void engine_네이밍테스트1() {
        car.set(new TOYOTA());
        String expectedName = "TOYOTA";
        assertEquals(expectedName, car.engine.getName());
    }

    @Test
    public void engine_네이밍테스트2() {
        car.set(new WIA());
        String expectedName = "WIA";
        assertEquals(expectedName, car.engine.getName());
    }

    @Test
    public void engine_네이밍테스트3() {
        car.set(new Broken());
        String expectedName = "고장난 엔진";
        assertEquals(expectedName, car.engine.getName());
    }

    @Test
    public void engine_네이밍테스트4() {
        car.set(new GM());
        String expectedName = "GM";
        assertEquals(expectedName, car.engine.getName());
    }


    @Test
    public void brake_네이밍테스트2() {
        car.set(new Mando());
        String expectedName = "Mando";
        assertEquals(expectedName, car.breakSystem.getName());
    }
    @Test
    public void brake_네이밍테스트3() {
        car.set(new Continental());
        String expectedName = "Continental";
        assertEquals(expectedName, car.breakSystem.getName());
    }

    @Test
    public void main테스트(){
        Assemble am = new Assemble();

    }
}