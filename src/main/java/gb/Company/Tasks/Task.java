package gb.Company.Tasks;

import gb.Company.Person.Employee;

import java.time.LocalDate;

public class Task {
    private String description;
    private Priority priority;
    private Status status;
    private int idTask;
    private LocalDate dataStart;
    private LocalDate dataFinish;
    private Employee executor; // исполнитель
    static private int uid = 0;


    public Task(String description, Priority priority){
        this.description = description;
        this.priority = priority;
        this.status = Status.NEW;
        this.idTask = ++uid;
    }

    public int getIdTask() {
        return idTask;
    }

    public Employee getExecutor() {
        return executor;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setExecutor(Employee executor) {
        this.executor = executor;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String info = "ID: " + this.idTask + ", DESCR: " + this.description + ", STATUS: " + this.status +
                ", Priority: " + this.priority;
        return info;
    }

    public void setDataStart() {
        this.dataStart = LocalDate.now();
    }

    public void setDataFinish() {
        this.dataFinish = LocalDate.now();
    }
}
