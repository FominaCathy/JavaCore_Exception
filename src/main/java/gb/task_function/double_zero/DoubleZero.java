package gb.task_function.double_zero;

public class DoubleZero {
    /**
     * возвращает истину, если в переданном целочисленном массиве есть два соседних элемента, с нулевым значением
     *
     * @param array целочисленный массив
     * @return true / false
     */
    public static boolean checkDoubleZero(int[] array) {

        boolean flag = false;
        int start = 0;
        int end = array.length - 1;
        while ((!flag) && start < end) {
            if (array[start] == 0 & array[start + 1] == 0) {
                flag = true;
            } else {
                start++;
            }
        }
        return flag;
    }

    private static String doubleZeroArray(int[] array) {

        String msg;

        if (DoubleZero.checkDoubleZero(array)) {
            msg = "в переданном  массиве ЕСТЬ два соседних элемента, с нулевым значением ";
        } else {
            msg = "в переданном  массиве НЕТ двух соседних элементов, с нулевым значением ";
        }
        return msg;
    }

}
