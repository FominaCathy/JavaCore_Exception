package gb.Company.Tasks;


import java.util.Comparator;

public class DataTaskComparator  implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getCreateData().compareTo(o2.getCreateData());
    }
}
