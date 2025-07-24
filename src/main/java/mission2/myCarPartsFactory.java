package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.*;

public class myCarPartsFactory implements CarPartsFactory{
    @Override
    public CarType createCarType(int part) {
        switch(part){
            case 1:
                return new Sedan();
            case 2:
                return new SUV();
            case 3:
                return new Truck();
            default:
                return null;
        }
    }

    @Override
    public Engine createEngine(int part) {
        switch(part){
            case 1:
                return new GM();
            case 2:
                return new TOYOTA();
            case 3:
                return new WIA();
            case 4:
                return new Broken();
            default:
                return null;
        }
    }

    @Override
    public BrakeSystem createBrake(int part) {
        switch(part){
            case 1:
                return new Mando();
            case 2:
                return new Continental();
            case 3:
                return new Bosch_B();
            default:
                return null;
        }
    }


    @Override
    public SteeringSystem createSteering(int part) {
        switch(part){
            case 1:
                return new Bosch_S();
            case 2:
                return new Mobis();
            default:
                return null;
        }
    }

}
