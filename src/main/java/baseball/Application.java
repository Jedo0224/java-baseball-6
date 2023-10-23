package baseball;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    //길이 및 중복성 검사
    public static void checkLength(String[] input){

        if(input.length > 3){
            throw new IllegalArgumentException("입력된 숫자가 세자리 이상이다.");
        }
    }

    //입력값 유효성 검사
    public static int parseAndVaiidateInput(String s){

        try{
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력된 숫자가 int형이 아니다.");

        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        do {
            List<Integer> computerNumbers = generateComputerNumbers();
            System.out.println("숫자 야구게임을 시작합니다.");
            System.out.println("타겟 숫자: " + computerNumbers);

            while (true) {

                System.out.print("숫자를 입력해주세요. : ");
                String[] userInput = scanner.nextLine().split("");;
                checkLength(userInput);

                int strike = 0, ball = 0;
                for (int i = 0; i < userInput.length; i++) {
                    int number = parseAndVaiidateInput(userInput[i]);
                    if (computerNumbers.get(i) == number) {
                        strike++;
                        continue;
                    } else if (computerNumbers.contains(number)) {
                        ball++;
                    }
                }

                printResult(strike, ball);
                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    break;
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");

            if (scanner.nextLine().equals("2")) {
                break;
            }

        } while (true);

    }

    private static List<Integer> generateComputerNumbers() {

        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < 3) {
            Randoms Randoms = null;
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    private static void printResult(int strike, int ball){

        if (strike != 0 && ball != 0) {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        } else if (strike == 0 && ball != 0) {
            System.out.println(ball + "볼 ");
        } else if (strike != 0) {
            System.out.println(strike + "스트라이크");
        } else {
            System.out.println("낫싱");
        }
    }



}
