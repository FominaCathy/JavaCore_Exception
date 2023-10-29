package gb.Company.Tasks;

import gb.Company.Person.Employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

public class Task implements Comparator<Task>, Serializable {
    private String description;
    private Priority priority;
    private Status status;
    private int idTask;
    private LocalDateTime createData;
    private LocalDate dataStart;
    private LocalDate dataFinish;
    private Employee executor; // исполнитель
    static private int uid = 0;


    public Task(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
        this.status = Status.NEW;
        this.createData = LocalDateTime.now();
        this.idTask = ++uid;
    }

    public LocalDateTime getCreateData() {
        return createData;
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
        if (executor == null){
            System.out.println("не указан исполнитель");
        } else {
            this.executor = executor;
        }
    }

    /**
     * изменить статус может только сам исполнитель, либо его руководитель
     *
     * @param status   - устанавливаемый статус
     * @param employee - инициатор смены статуса
     * @throws RuntimeException
     */
    public void setStatus(Status status, Employee employee) throws RuntimeException {
        if (checkAccess(employee)){ //сотрудник имеет право менять статус
            this.status = status;
        }

    }

    private boolean checkAccess(Employee employee) throws RuntimeException {
        // проверка прав на смену статуса. статус может сменить либо сам менеджер либо руководитель департамента
        if (this.executor == null) {
            throw new RuntimeException("Задаче не назначен исполнитель. Изметить статус нельзя");
        } else if (!employee.getDepartament().equals(this.executor.getDepartament())) {
            throw new RuntimeException("Сотрудник из стороннего отдела не может изменить статус задачи.");
        } else if (!(this.executor.equals(employee) && employee.getDepartament().equals(this.executor.getDepartament()))) {
            throw new RuntimeException("Сотрудник  не может изменить статус задачи.");
        }
      return true;

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

    public LocalDate getDataStart() {
        return dataStart;
    }

    public LocalDate getDataFinish() {
        return dataFinish;
    }

    public static int getUid() {
        return uid;
    }


    @Override
    public int compare(Task o1, Task o2) {
        return o1.priority.ordinal() - o2.priority.ordinal();
    }
}
