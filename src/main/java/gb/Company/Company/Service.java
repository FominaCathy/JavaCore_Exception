package gb.Company.Company;

import gb.Company.Person.Employee;
import gb.Company.Tasks.Status;
import gb.Company.Tasks.Task;

import java.util.ArrayList;

public class Service {

    PlannerTasks plannerTasks;
    StaffEmployees company;

    public Service() {
        this.plannerTasks = new PlannerTasks();
        this.company = new StaffEmployees();

    }

    /**
     * печать списка задач
     */
    public void printPlanner() {
        System.out.println("List planner:");
        for (Task item : plannerTasks.getTaskList()) {
            System.out.print(item.toString());
            if (item.getExecutor() != null) {
                System.out.println(
                        " - EXECUTOR: " + item.getExecutor().getFirstName() + " " + item.getExecutor().getLastName());
            } else {
                System.out.println(" - EXECUTOR: ---");
            }

        }
        System.out.println("------");
    }

    /**
     * добавление задачи в список задач
     * @param task - задача
     */
    public void addTask(Task task) {
        plannerTasks.addTask(task);
    }

    /**
     * печать списка сотрудников с полной инф-ции о них
     */
    public void printEmployee() {
        for (Employee item : company.getStaff()) {
            System.out.println(item.toString());
        }
    }

    /**
     * добавление сотрудника
     * @param employee сотрудник или руководитель
     */
    public void addEmployee(Employee employee) {
        company.addEmployee(employee);
    }

    /**
     * изменение з/п всем сотрудникам, старше указанного возраста
     * @param age - возраст
     * @param sumChange - сумма изменения
     * @return
     */
    public boolean changeSalaryAll( int age, double sumChange) {

        for (Employee employee : this.company.getStaff()) {
            employee.changeSalary(age, employee.getSalary() + sumChange);
        }
        return true;
    }

    /**
     * расчет среднего возраста в компании
     * @return средний возраст в компании
     */
    public double averageAge() {
        double sumAge = 0;
        for (Employee employee : this.company.getStaff()) {
            sumAge += employee.getAge();
        }

        return  sumAge / this.company.getStaff().size();
    }

    /**
     * расчет средней з/п в компании
     * @return средняя з/п в компании
     */
    public double averageSalary() {
        double sumSalary = 0;
        for (Employee employee : this.company.getStaff()) {
            sumSalary += employee.getSalary();
        }

        return sumSalary / this.company.getStaff().size();
    }

    /**
     * печать листа с ЗП все компании
     */
    public void printListSalary() {
        System.out.println("\n List Salary:");
        for (Employee employee : this.company.getStaff()) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() +
                    " " + employee.getAge() + " yers" +
                    " - " + employee.getSalary() + " rub");
        }
    }

    /**
     * назначение задачи по ее номеру на исполнителя
     * @param numberTask - номер задачи
     * @param fullName - полное имя исполнителя
     */
    public void setExecutorTask(int numberTask, String fullName) {
        Employee executor = company.getForName(fullName);
        Task task = plannerTasks.getForId(numberTask);

        task.setExecutor(executor);

    }

    /**
     * печать всех задач сотрудника
     * @param fullName - полное имя сотрудника
     */
    public void printAllTaskEmployee(String fullName) {
        Employee employee = company.getForName(fullName);
        System.out.println("Task list " + fullName);
        boolean flag = false;
        for (Task task : plannerTasks.taskList) {
            if ((task.getExecutor() != null) && (task.getExecutor().equals(employee))) {
                System.out.println(task.toString() );
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Task not found \n");
        }
    }

    /**
     * взятие задачи в работу
     * @param fullName - полное имя исполителя
     * @param idTask - id номер задачи
     */
    public void StartTask(String fullName, int idTask) {

        if (setStatusTask(Status.PROGRESS, fullName, idTask)) {
            System.out.println("Статус изменен на :" + Status.PROGRESS.toString() +"\n");
        } else {
            System.out.println("Что-то пошло не так \n");
        }
        ;
    }

    /**
     * закрытие задачи
     * @param fullName - полное имя исполителя
     * @param idTask - id номер задачи
     */
    public void FinishTask(String fullName, int idTask) {

        if (setStatusTask(Status.CLOSE, fullName, idTask)) {
            System.out.println("Статус изменен на :" + Status.CLOSE.toString());
        } else {
            System.out.println("Что-то пошло не так");
        }
        ;
    }

    /**
     * изменение статуса задачи. изменить статус задачи может только сотрудник департамента которому принадлежит исполнитель
     * (сам сотридник либо менеджер его департамента)
     *
     * @param status   - устанавливаемый статус
     * @param fullName - полное имя исполителя
     * @param idTask   - id номер задачи
     * @return - возвращает успешность операции
     */
    private boolean setStatusTask(Status status, String fullName, int idTask) {
        Employee employee = company.getForName(fullName);
        Task task = plannerTasks.getForId(idTask);
        // либо сам менеджер либо менеджер из его департамента
        if ((task.getExecutor() != null) && (task.getExecutor().getDepartament().equals(employee.getDepartament()))) {
            task.setStatus(status);
            if (status == Status.PROGRESS) {
                task.setDataStart();
            } else if (status == Status.CLOSE) {
                task.setDataFinish();
            }
        } else {

            return false;
        }
        return true;
    }

    //метод повышения з/п, модифицируйте метод таким образом, чтобы он мог поднять з/п всем, кроме руководителей.

    /**
     * повышение з/п всем кроме руководителей
     * @param sum
     */
    public void increaseSalary(double sum) {
        for (Employee item : this.company.getStaff()) {
            if (item.getClass().equals(Employee.class)){
                item.changeSalary(0, 10000);
            }

        }

    }

    /**
     * назначение задачи с учетом приоритета: если у исполнителя есть менее приоритетная задача -
     * то ей устанавлвается статус inHold (удержание)
     *
     * @param idTask   - id номер задачи
     * @param fullName - полное имя исполнителя
     */
    public void setExecutorTaskOrderPriority(int idTask, String fullName) {
        Employee employee = company.getForName(fullName);
        Task task = plannerTasks.getForId(idTask);

        ArrayList<Task> taskListEmployee = new ArrayList<>();
        for (Task item : plannerTasks.taskList) {
            if ((item.getExecutor() != null) && (item.getExecutor().equals(employee))) {
                taskListEmployee.add(item);
            }
        }

        if (!taskListEmployee.isEmpty()) {
            // выбираем менее приоритетную задачу
            Task currTask = task;
            for (Task item : taskListEmployee) {
                if (currTask.getStatus().ordinal() < item.getStatus().ordinal()) {
                    currTask = item;
                }
            }
            currTask.setStatus(Status.ONHOLD); //устанавливаем менее приоритетной задаче статус ONHOLD

        }
        task.setExecutor(employee); // назначаем новую задачу на исполнителя
    }
}

