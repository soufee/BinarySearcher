import java.util.Random;
import java.util.Scanner;

public class RandomNumFinder {
    private int q;

    public RandomNumFinder(int q) {
        this.q = q;
    }

    public void run() {
        if (q == 1) computersNumber();
        else if (q == 0) usersNumber();
        else
            System.out.println("объект создан с неправильным номером. Должно быть 1 для загадывания цифры компом или 0, если комп будет угадывать");
    }

    public void usersNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Загадайте число от 1 до 1000, а я попробую его угадать. Если я назвал вашу цифру, вы говорите: ДА. Если нет, вы говорите БОЛЬШЕ или МЕНЬШЕ");
        int count = 0;
        int upperBound = 1000;
        int lowerBound = 1;
        int myNum = upperBound/2;
        String yourAnswer;
        System.out.println("Итак, начнем. Это цифра "+myNum+"?");{
            while (true){
                count++;
                yourAnswer = scanner.nextLine();
                if (yourAnswer.equalsIgnoreCase("ДА")){
                    System.out.println("Ура, я угадал! Для этого мне понадобилось "+count+" шагов.");
                    break;
                } else if(yourAnswer.equalsIgnoreCase("БОЛЬШЕ")) {
                    lowerBound = myNum;
                    myNum = lowerBound + ((upperBound-lowerBound)/2);
                    System.out.println("Шаг "+count+": Может эта цифра "+myNum+"?");
                } else if (yourAnswer.equalsIgnoreCase("МЕНЬШЕ")){
                    upperBound = myNum;
                    myNum = myNum-((upperBound-lowerBound)/2);
                    System.out.println("Шаг "+count+": Может эта цифра "+myNum+"?");
                } else {
                    System.out.println("Не хулигань! Введи ДА, БОЛЬШЕ или МЕНЬШЕ");
                    count--;
                }
            }
        }

    }

    public void computersNumber() {
        Random random = new Random();
        int number = random.nextInt(1000) + 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Я загадал число от 1 до 1000. Попробуй его угадать");
        int yourNum;
        int count = 0;
        while (true) {
            yourNum = scanner.nextInt();
            count++;
            if (yourNum == number) {
                System.out.println("Шаг " + count + ": Вы угадали. Компьютер загадал цифру " + number);
                break;
            } else if (yourNum > number) {
                System.out.println("Шаг " + count + ": Вы не угадали. Загаданное число меньше " + yourNum);
            } else if (yourNum < number) {
                System.out.println("Шаг " + count + ": Вы не угадали. Загаданное число больше " + yourNum);
            }
        }
    }

}
