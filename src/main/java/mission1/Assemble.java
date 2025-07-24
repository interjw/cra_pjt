import java.util.Scanner;

public class Assemble {

    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int FirstStep = 0;
    private static final int CarType = 0;
    private static final int Engine = 1;
    private static final int BrakeSystem = 2;
    private static final int SteeringSystem = 3;
    private static final int Run_Test       = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final String[] TYPE_NAME = {"","Sedan","SUV","Truck"};
    private static final int GM = 1, TOYOTA = 2, WIA = 3 ,BrokenEngine = 4;
    private static final String[] ENGINE_NAME = {"","GM","TOYOTA","WIA","고장난 엔진"};
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final String[] BRAKE_NAME = {"","MANDO","CONTINENTAL","BOSCH"};
    private static final int BOSCH_S = 1, MOBIS = 2;
    private static final String[] STREERING_NAME = {"","BOSCH","MOBIS"};

    public static final int DELAY_MS = 800;
    public static final int PRE_TEST_DELAY_MS = 1500;
    public static final int TEST_DELAY_MS = 2000;
    public static final int NOT_NUMBER = -1;
    public static final int BACKWARD = 0;

    private static int[] myCar = new int[5];

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

    private static int doAssembleAndTest(int currentStep, int answer) {
        switch (currentStep) {
            case CarType:
                selectCarType(answer);
                delay(DELAY_MS);
                currentStep = Engine;
                break;
            case Engine:
                selectEngine(answer);
                delay(DELAY_MS);
                currentStep = BrakeSystem;
                break;
            case BrakeSystem:
                selectBrakeSystem(answer);
                delay(DELAY_MS);
                currentStep = SteeringSystem;
                break;
            case SteeringSystem:
                selectSteeringSystem(answer);
                delay(DELAY_MS);
                currentStep = Run_Test;
                break;
            case Run_Test:
                if (answer == 1) {
                    runProducedCar();
                } else if (answer == 2) {
                    System.out.println("Test...");
                    delay(PRE_TEST_DELAY_MS);
                    testProducedCar();
                }
                delay(TEST_DELAY_MS);
                break;
        }
        return currentStep;
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
            case CarType:
                showCarTypeMenu();
                break;
            case Engine:
                showEngineMenu();
                break;
            case BrakeSystem:
                showBrakeMenu();
                break;
            case SteeringSystem:
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

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }
    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }
    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }
    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }
    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CarType:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem:
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

    private static void selectCarType(int answer) {
        myCar[CarType] = answer;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", TYPE_NAME[answer]);
    }

    private static void selectEngine(int answer) {
        myCar[Engine] = answer;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", ENGINE_NAME[answer]);
    }
    private static void selectBrakeSystem(int answer) {
        myCar[BrakeSystem] = answer;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", BRAKE_NAME[answer]);
    }
    private static void selectSteeringSystem(int answer) {
        myCar[SteeringSystem] = answer;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", STREERING_NAME[answer]);
    }


    private static boolean isValidCheck() {
        if (myCar[CarType] == SEDAN && myCar[BrakeSystem] == CONTINENTAL) return false;
        if (myCar[CarType] == SUV   && myCar[Engine] == TOYOTA)       return false;
        if (myCar[CarType] == TRUCK && myCar[Engine] == WIA)          return false;
        if (myCar[CarType] == TRUCK && myCar[BrakeSystem] == MANDO)  return false;
        if (myCar[BrakeSystem] == BOSCH_B && myCar[SteeringSystem] != BOSCH_S) return false;
        return true;
    }

    private static void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (myCar[Engine] == BrokenEngine) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        printRunResult();
    }

    private static void printRunResult() {
        System.out.printf("Car Type : %s\n", TYPE_NAME[myCar[CarType]]);
        System.out.printf("Engine   : %s\n", ENGINE_NAME[myCar[Engine]]);
        System.out.printf("Brake    : %s\n", BRAKE_NAME[myCar[BrakeSystem]]);
        System.out.printf("Steering : %s\n", STREERING_NAME[myCar[SteeringSystem]]);
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        if (myCar[CarType] == SEDAN && myCar[BrakeSystem] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (myCar[CarType] == SUV && myCar[Engine] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (myCar[CarType] == TRUCK && myCar[Engine] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (myCar[CarType] == TRUCK && myCar[BrakeSystem] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (myCar[BrakeSystem] == BOSCH_B && myCar[SteeringSystem] != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}