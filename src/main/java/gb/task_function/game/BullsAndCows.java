package gb.task_function.game;


public class BullsAndCows {
    /**
     * игрa Быки и Коровы, числовой вариант либо со словами. Дать пользователю 3-5 попыток угадать загаданное.
     * коровы - количество угаданного без совпадения с их позициями
     * быки - полное совпадение
     */
    private String secret;
    private int[] resVerify = new int[2];
    private char[] secretString;
    public int lengthSecret;

    public BullsAndCows(String string) {
        this.secret = string;
        this.secretString = secret.toCharArray();
        this.lengthSecret = secretString.length;
    }

    public String toString() {
        return this.secret;
    }

    /**
     * считает кол-во быков и коров в заданном слове
     *
     * @param choiceGamer проверяемое слово
     * @return массив с 2мя значениями: колво быков и кол-во коров
     */
    private int[] verify(String choiceGamer) {

        int bulls = 0;
        int cows = 0;
        char[] verifyChoice = choiceGamer.toCharArray();

        // count bulls and cows
        for (int i = 0; i < lengthSecret; i++) {
            if (verifyChoice[i] == this.secretString[i]) {
                bulls++;
            } else {
                for (char item : secretString) {
                    if (verifyChoice[i] == item) {
                        cows++;
                    }
                }
            }
        }
        resVerify[0] = bulls;
        resVerify[1] = cows;

        return resVerify;
    }

    /**
     * возвращает состояние угаданных букв
     * @param choiceGamer проверяемое слово
     * @return текстовую строку для передачи ее пользоввателю
     */
    public String resVerify(String choiceGamer) {

        int[] res = verify(choiceGamer);
        return "результат: " + res[0] + " ,бык/быков и " + res[1] + " корова/коров";
    }
}
