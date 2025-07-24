package mission2;

import mission2.BrakeSystem.BrakeSystem;
import mission2.CarType.CarType;
import mission2.Engine.Engine;
import mission2.SteeringSystem.SteeringSystem;

public class MyCar {
    CarType carType;
    Engine engine;
    BrakeSystem breakSystem;
    SteeringSystem steeringSystem;

    void set(CarType car){
        carType = car;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", car.getName());
    }
    void set(Engine eng){
        engine = eng;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", eng.getName());
    }

    void set(BrakeSystem bs){
        breakSystem = bs;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", bs.getName());
    }

    void set(SteeringSystem ss){
        steeringSystem = ss;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", ss.getName());
    }
}
