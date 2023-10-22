package gb.Company.Company;

import gb.Company.Person.Employee;
import gb.Company.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class PlannerTasks {
    List<Task> taskList;


    public PlannerTasks() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setExecutor(Task task, Employee executor) {
    }

    public Task getForId(int idTask) {
        for (Task task : taskList) {
            if (task.getIdTask() == idTask){
                return task;
            }
        }
        return null;
    }
}
