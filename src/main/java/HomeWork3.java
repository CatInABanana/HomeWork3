import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class HomeWork3 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start(){
        do {
            int answer;
            System.out.println("Выберите игру. 1 - Угадай число, 2 - Угадай слово, 0 - Выход из программы. ");
            while (!scanner.hasNextInt()){
                System.out.println("Повторите ввод: ");
            }
            answer = scanner.nextInt();
            scanner.nextLine();
            if (answer == 1){
                game1();
                repeatGame(1);
            } else if (answer == 2){
                game2();
                repeatGame(2);
            } else if (answer == 0){
                System.out.println("Выход из программы. ");
                break;
            } else System.out.println("Повторите ввод: ");
        } while (true);
    }

    private static void game1() {

        /*Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
        При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
        После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
         */

        Random random = new Random();
        System.out.println("Компьютер загадал число от 0 до 9. ");
        int number = random.nextInt(10);
        //System.out.println(number);
        int input;
        boolean win = false;
        for (int counter = 3; counter > 0; counter--) {
            System.out.println("Угадайте загаданное число c 3 попыток: ");
            do {
                while (!scanner.hasNextInt()){
                    System.out.println("Можно ввести только число");
                    scanner.nextLine();
                }
                input = scanner.nextInt();
                scanner.nextLine();
            } while ((input < 0) || (input > 9));
            if (number == input){
                System.out.println("Вы победили. ");
                win = true;
                break;
            }
            else if (number > input) System.out.println("Загаданное число больше введенного. ");
            else System.out.println("Загаданное число меньше введенного. ");
            System.out.println("Попыток осталось: " + (counter - 1));
        }
        if (!win) System.out.println("Вы проиграли. ");
        //repeatGame(1);
    }

    private static void repeatGame(int game) {

        do {
            String answer;
            System.out.println("Начать новую игру? y - да, n - нет. ");
            while (!scanner.hasNext("[a-zA-Z]")) {
                System.out.println("Можно ввести только одну латинскую букву. Повторите ввод: ");
                scanner.nextLine();
            }
            answer = scanner.next().toLowerCase();
            scanner.nextLine();
            if (answer.equals("y")) {
                if (game == 1) game1();
                else if (game == 2) game2();
            } else if (answer.equals("n")) {

                break;
            } else System.out.print("Повторите ввод: ");
            scanner.nextLine();
        } while (true);

    }

    public static void game2(){

        Random random = new Random();
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String word = words[random.nextInt(words.length)];
        //System.out.println(word);
        System.out.println("Программа загада слово из следующего saсписка:\n" +
                "   apple, orange, lemon, banana, apricot, avocado, broccoli, carrot, \n" +
                "   cherry, garlic, grape, melon, leak, kiwi, mango, mushroom, nut, \n" +
                "   olive, pea, peanut, pear, pepper, pineapple, pumpkin, potato");
        do {
            String input;
            System.out.println("Угадайте загаданное слово: ");
            while (!scanner.hasNext("[a-zA-Z]*")) {
                System.out.println("Можно ввести только латинские буквы. Повторите ввод: ");
                scanner.nextLine();
            }
            input = scanner.next().toLowerCase();
            scanner.nextLine();
            if (input.equals(word)) {
                System.out.println("Победа! ");
                break;
            } else if (getIndexes(input, word) > 0) {
                System.out.println("Есть совпадение(я): ");
                printMask(getMask(input, word));
            } else System.out.println("Совпадений не найдено. ");
        } while (true);

    }

    public static int getIndexes(String input,String word){
        int lenght = 0;
        for (int i = 0; i < (Math.min(input.length(), word.length())); i++) {
            if (input.charAt(i) == word.charAt(i)) {
                lenght++;
            }
        }
        return lenght;
    }

    private static char[] getMask(String input,String word) {
        char[] chars = new char[15];
        int maxLenght = Math.min(input.length(), word.length());
        for (int i = 0; i < chars.length; i++) {
            if (i < maxLenght) {
                for (int j = 0; j < maxLenght; j++) {
                    if (input.charAt(j) == word.charAt(j)) {
                        chars[j] = input.charAt(j);
                    } else chars[j] = '#';
                }
            } else chars[i] = '#';
        }
        return chars;
    }

    private static void printMask(char[] chars) {
        for (char Char : chars) {
            System.out.print(Char);
        }
        System.out.println();
    }

}