package gb.Company.Company;

import gb.Company.Person.Employee;
import gb.Company.Tasks.DataTaskComparator;
import gb.Company.Tasks.PriorityTaskComparator;
import gb.Company.Tasks.Status;
import gb.Company.Tasks.Task;

import java.util.*;

/**
 * класс для работы с задачами. Сначала задача попадает в Backlog, после назначения  исполнителя - в планнер
 */
public class PlannerTasks {
    private ArrayList<Task> backlog;


    public PlannerTasks() {
        this.backlog = new ArrayList<>();
    }

    public ArrayList<Task> getBacklog() {
        return backlog;
    }

    public void addTask(Task task) throws RuntimeException {
        if (task.getStatus() == Status.CLOSE) {
            throw new RuntimeException("нельзя добавить в backlog закрытую задачу");
        }
        if (backlog.contains(task)) {
            throw new RuntimeException("эта задача уже есть в backlog");
        }
        backlog.add(task);
        // при добавлении задачи - список сразу сортируется
        Collections.sort(backlog, new PriorityTaskComparator().thenComparing(new DataTaskComparator()));

    }

    public ArrayList<Task> getTasksEmployee(Employee employee) {
        ArrayList<Task> taskListEmployee = new ArrayList<>();
        for (Task item : this.backlog) {
            if ((item.getExecutor() != null) && (item.getExecutor().equals(employee))) {
                taskListEmployee.add(item);
            }
        }
        return taskListEmployee;
    }

    private ArrayList<Task> getTasksEmployeeForStatus(Employee employee, Status status) {
        ArrayList<Task> taskListEmployee = new ArrayList<>();
        for (Task item : this.backlog) {
            if ((item.getExecutor() != null) && (item.getExecutor().equals(employee)) && item.getStatus().equals(status)) {
                taskListEmployee.add(item);
            }
        }
        return taskListEmployee;
    }

    /**
     * проверка преоритетности задач. : если у исполнителя в работе менее приоритетная задача -
     * то ей устанавлвается статус inHold (удержание)
     *
     * @param task     - задача
     * @param employee - исполнитель
     */

    public void checkTaskPriority(Task task, Employee employee) {
        ArrayList<Task> taskListEmployee = getTasksEmployeeForStatus(employee, Status.PROGRESS);
        if (taskListEmployee.isEmpty()) {
            task.setStatus(Status.PROGRESS, employee);
        } else {
            // выбираем менее приоритетную задачу
            Task currTask = task;
            for (Task item : taskListEmployee) {
                if (item.getPriority().ordinal() < currTask.getPriority().ordinal()) { //ищем самый низкий статус
                    currTask = item;
                }
            }
            if (currTask == task) {
                task.setStatus(Status.ONHOLD, employee);
            } else {
                currTask.setStatus(Status.ONHOLD, employee); //устанавливаем менее приоритетной задаче статус ONHOLD
                task.setStatus(Status.PROGRESS, employee);
            }
        }
        task.setExecutor(employee); // назначаем новую задачу на исполнителя

    }

    public Task getForId(int idTask) {
        for (Task task : backlog) {
            if (task.getIdTask() == idTask) {
                return task;
            }
        }
        return null;
    }

    /**
     * получение списка задач  с назначенным исполнителем планнера
     */
    public ArrayList<Task> getListPlanner() {
        ArrayList<Task> listPlanner = new ArrayList<>();

        for (Task item : this.getBacklog()) {

            if (item.getExecutor() != null) {
                listPlanner.add(item);
            }
      }
        Collections.sort(listPlanner, new PriorityTaskComparator().thenComparing(new DataTaskComparator()));
        return listPlanner;
    }

    /**
     * получение листа ожидания
     * @return
     */
    public ArrayList<Task> getListWaiting() {
        ArrayList<Task> listWaiting = new ArrayList<>();

        for (Task item : this.getBacklog()) {
            if (item.getExecutor() == null) {
                listWaiting.add(item);
            }
        }
        Collections.sort(listWaiting, new PriorityTaskComparator().thenComparing(new DataTaskComparator()));
        return listWaiting;
    }


    public void startTask(Task task, Employee employee) {
        ArrayList<Task> taskListEmployee = getTasksEmployee(employee);
        if (!taskListEmployee.isEmpty()) {
            checkTaskPriority(task, employee);
        }
    }

    public void closeTask(Task task, Employee employee) throws RuntimeException {
        if (task.getStatus() != Status.PROGRESS) {
            throw new RuntimeException("задачу в статусе " + task.getStatus().getTitle() + " - закрыть нельзя");
        }
        task.setStatus(Status.CLOSE, employee);
        ArrayList<Task> taskListEmployee = getTasksEmployeeForStatus(employee, Status.ONHOLD);
        Task nextTask = null;
        if (!taskListEmployee.isEmpty()) {
            for (Task item : taskListEmployee) {
                if (nextTask == null) {
                    nextTask = item;
                } else {
                    if (nextTask.getPriority().ordinal() < item.getPriority().ordinal()) {
                        nextTask = item;

                    }
                }
            }
            nextTask.setStatus(Status.PROGRESS, employee);
        }

    }


}
