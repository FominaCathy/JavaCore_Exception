package gb.Company.Tasks;

import gb.Company.Company.PlannerTasks;

import java.util.Comparator;
import java.util.List;

public class PriorityTaskComparator  implements Comparator<Task>{

    @Override
    public int compare(Task o1, Task o2) {
//        return o1.getPriority().ordinal() - o2.getPriority().ordinal();
        return  o2.getPriority().ordinal() - o1.getPriority().ordinal(); //сначала важные


    }
}
