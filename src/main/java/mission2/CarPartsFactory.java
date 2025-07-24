package mission2;

import mission2.BrakeSystem.BrakeSystem;
import mission2.CarType.CarType;
import mission2.Engine.Engine;
import mission2.SteeringSystem.SteeringSystem;

public interface CarPartsFactory {
    CarType createCarType(int part);
    Engine createEngine(int part);
    BrakeSystem createBreake(int part);
    SteeringSystem createSterring(int part);
}
