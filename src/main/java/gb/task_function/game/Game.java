package gb.task_function.game;

import java.util.Scanner;

public class Game {

    private static int maxChoice = 5;

    /**
     * меню для игры
     */
    public static void menuGame() {
        boolean flagExit = false;
        while (!flagExit) {

            System.out.println("играем в быки и коровы. выбери что мне загадать: " +
                    "\n 1 - число" +
                    "\n 2 - слово" +
                    "\n любой другой символ - закончить игру");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    gameBullsAndCows(1);
                    break;
                case "2":
                    gameBullsAndCows(2);
                    break;
                default:
                    flagExit = true;
                    System.out.println("пока-пока!");
                    break;
            }

        }

    }

    /**
     * игра быки и коровы
     * @param choiceMenu
     */
    private static void gameBullsAndCows(int choiceMenu) {
        String randomSecret = null;
        if (choiceMenu == 1) {
            randomSecret = "1234";
        } else {
            randomSecret = "duck";
        }
        BullsAndCows secret = new BullsAndCows(randomSecret);

        boolean game = true;
        int choice = 0;
        int countChar = secret.lengthSecret;

        while (game & (choice < maxChoice)) {
            choice++;
            Scanner input = new Scanner(System.in);
            System.out.println("Попытка " + choice + " из " + maxChoice);
            System.out.println("введите ваш вариант из " + countChar + " букв:");
            String choiceGamer = input.nextLine();

            if (choiceGamer.length() == countChar) {
                if (secret.toString().equals(choiceGamer)) {
                    System.out.println("Ура. вы угадали. Это действительно:  " + secret);
                    game = false;
                } else {
                    System.out.println(secret.resVerify(choiceGamer));
                }
            } else {
                System.out.println("кол-во букв не совпадает. должно быть " + countChar + " попробуй еще");
            }

        }
        if (game) {
            System.out.println("Увы, вы не угадали. Было загадано: " + secret);
        }
    }

}
