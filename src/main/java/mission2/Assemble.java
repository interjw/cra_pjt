package mission2;

import mission2.BrakeSystem.*;
import mission2.CarType.*;
import mission2.Engine.*;
import mission2.SteeringSystem.Bosch_S;
import mission2.SteeringSystem.SteeringSystem;

import java.util.Scanner;
import static mission2.Menu.*;

public class Assemble {

    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final int FirstStep = 0;

    private static final int CAR_TYPE = 0;
    private static final int ENGINE_SYSTEM = 1;
    private static final int BRAKE_SYSTEM = 2;
    private static final int STEERING_SYSTEM = 3;
    private static final int Run_Test       = 4;

    public static final int DELAY_MS = 800;
    public static final int TEST_DELAY_MS = 2000;
    public static final int NOT_NUMBER = -1;
    public static final int BACKWARD = 0;

    static myCarPartsFactory factory = new myCarPartsFactory();
    private static MyCar myCar = new MyCar();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int currentStep = FirstStep;

        while (true) {
            initScreen();
            showMenu(currentStep);
            String buf =  getString(sc);
            if (isExit(buf)) break;

            Integer answer = getInteger(buf, currentStep);
            if (answer == null) continue;

            currentStep = doAction(answer, currentStep);
        }

        sc.close();
    }

    private static boolean isExit(String buf) {
        return buf == null;
    }

    private static Integer getInteger(String buf, int currentStep) {
        int answer = getAnswer(buf);
        if (isUnvalidCheck(currentStep, answer)) return null;
        return answer;
    }

    private static int doAction(int answer, int currentStep) {
        return goBackOrAssembleOrTest(answer, currentStep);
    }

    private static int goBackOrAssembleOrTest(int answer, int currentStep) {
        if (answer == BACKWARD) {
            currentStep = moveStep(currentStep);
            return currentStep;
        }

        currentStep = doAssembleAndTest(currentStep, answer);
        return currentStep;
    }

    private static int getAnswer(String buf) {
        int answer;
        try {
            answer = Integer.parseInt(buf);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(DELAY_MS);
            return NOT_NUMBER;
        }
        return answer;
    }

    public static int doAssembleAndTest(int currentStep, int answer) {
        switch (currentStep) {
            case CAR_TYPE:
                myCar.set(factory.createCarType(answer));
                delay(DELAY_MS);
                currentStep = ENGINE_SYSTEM;
                break;
            case ENGINE_SYSTEM:
                myCar.set(factory.createEngine(answer));
                delay(DELAY_MS);
                currentStep = BRAKE_SYSTEM;
                break;
            case BRAKE_SYSTEM:
                myCar.set(factory.createBreake(answer));
                delay(DELAY_MS);
                currentStep = STEERING_SYSTEM;
                break;
            case STEERING_SYSTEM:
                myCar.set(factory.createSterring(answer));
                delay(DELAY_MS);
                currentStep = Run_Test;
                break;
            case Run_Test:
                testCar(answer);
                break;
        }
        return currentStep;
    }

    private static void testCar(int answer) {
        TestCar tcCar = new TestCar(myCar);
        if (answer == 1) {
            tcCar.runProducedCar();
        } else if (answer == 2) {
            tcCar.testProducedCar();
        }
        delay(TEST_DELAY_MS);
    }

    private static String getString(Scanner sc) {
        System.out.print("INPUT > ");
        String buf = sc.nextLine().trim();

        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return null;
        }
        return buf;
    }

    private static boolean isUnvalidCheck(int currentStep, int answer) {
        if( answer == NOT_NUMBER)  return true;

        if (isValidRange(currentStep, answer)) {
            return false;
        }
        delay(DELAY_MS);
        return true;
    }

    private static int moveStep(int currentStep) {
        if (currentStep == Run_Test) {
            currentStep = goFirstStep();
        } else if (currentStep > FirstStep) {
            currentStep = goBackStep(currentStep);
        }
        return currentStep;
    }

    private static int goFirstStep() {
        return FirstStep;
    }

    private static int goBackStep(int currentStep) {
        currentStep--;
        return currentStep;
    }

    private static void showMenu(int step) {
        switch (step) {
            case CAR_TYPE:
                showCarTypeMenu();
                break;
            case ENGINE_SYSTEM:
                showEngineMenu();
                break;
            case BRAKE_SYSTEM:
                showBrakeMenu();
                break;
            case STEERING_SYSTEM:
                showSteeringMenu();
                break;
            case Run_Test:
                showRunTestMenu();
                break;
        }
    }

    private static void initScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }


    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CAR_TYPE:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case ENGINE_SYSTEM:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BRAKE_SYSTEM:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case STEERING_SYSTEM:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }



    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}