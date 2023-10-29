package gb.Company.Company;

import gb.Company.Person.Employee;
import gb.Company.Person.Posts;
import gb.Company.Tasks.Status;
import gb.Company.Tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Service {
    PlannerTasks plannerTasks;
    StaffEmployees company;

    public Service() {
        this.plannerTasks = new PlannerTasks();
        this.company = new StaffEmployees();
    }


    /**
     * добавление задачи в список задач
     *
     * @param task - задача
     */
    public void addTask(Task task) {
        try {
            plannerTasks.addTask(task);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * печать списка сотрудников с краткой инф-цией о них
     */
    public void printListEmployee() {

        for (Employee item : company.getStaff()) {
            System.out.println(item.shortInfo());
        }
    }

    /**
     * добавление сотрудника в штат
     *
     * @param employee сотрудник или руководитель
     */
    public void addEmployee(Employee employee) {
        company.addEmployee(employee);
    }

    /**
     * изменение з/п всем сотрудникам, старше указанного возраста
     *
     * @param age       - возраст
     * @param sumChange - сумма изменения
     * @return
     */
    public boolean changeSalaryAll(int age, double sumChange) {
        for (Employee employee : this.company.getStaff()) {
            employee.changeSalary(age, sumChange);
        }
        return true;
    }

    /**
     * расчет среднего возраста в компании
     *
     * @return средний возраст в компании
     */
    public double averageAge() {
        double sumAge = 0;
        for (Employee employee : this.company.getStaff()) {
            sumAge += employee.getAge();
        }

        return sumAge / this.company.getStaff().size();
    }

    /**
     * расчет средней з/п в компании
     *
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
     *
     * @param numberTask - номер задачи
     * @param fullName   - полное имя исполнителя
     */
    public void setExecutorTask(int numberTask, String fullName) {
        Employee executor = company.getForName(fullName);
        Task task = plannerTasks.getForId(numberTask);

        if ((task != null) && (executor != null)) {
            task.setExecutor(executor);
        }
    }

    /**
     * печать всех задач сотрудника
     *
     * @param fullName - полное имя сотрудника
     */
    public void printWorkTaskEmployee(String fullName) {
        Employee employee = company.getForName(fullName);
        if (employee == null) {
            return;
        }
        System.out.println("Task list " + fullName);
        ArrayList<Task> taskList = plannerTasks.getTasksEmployee(employee);
        if (taskList.isEmpty()) {
            System.out.println("Task not found \n");
        } else {
            for (Task task : taskList) {
                if (!task.getStatus().equals(Status.CLOSE)) {
                    System.out.println(task.toString());
                }
            }
        }
    }

    /**
     * взятие задачи в работу
     *
     * @param fullName - полное имя исполителя
     * @param idTask   - id номер задачи
     */
    public void startTask(String fullName, int idTask) {
        setStatusTask(Status.PROGRESS, fullName, idTask);
    }

    /**
     * закрытие задачи
     *
     * @param fullName - полное имя исполителя
     * @param idTask   - id номер задачи
     */
    public void closeTask(String fullName, int idTask) {
        setStatusTask(Status.CLOSE, fullName, idTask);
    }

    /**
     * изменение статуса задачи.
     *
     * @param status   - устанавливаемый статус
     * @param fullName - полное имя исполителя
     * @param idTask   - id номер задачи
     */
    private void setStatusTask(Status status, String fullName, int idTask) {
        Employee employee = company.getForName(fullName);
        Task task = plannerTasks.getForId(idTask);

        try {
            if (status == Status.PROGRESS) {
                plannerTasks.startTask(task, employee);
            } else if (status == Status.CLOSE) {
                plannerTasks.closeTask(task, employee);
            }
            System.out.println("Статус задачи изменен на :" + status.getTitle());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * повышение з/п всем кроме руководителей
     *
     * @param sum
     */
    public void increaseSalary(double sum) {
        try {
            for (Employee item : this.company.getStaff()) {
                if (item.getClass().equals(Employee.class)) {
                    item.changeSalary(0, 10000);
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    public void printPlanner() {
        printList(plannerTasks.getListPlanner(), "List Planner");
    }

    public void printListWaiting() {
        printList(plannerTasks.getListWaiting(), "List Waiting");
    }

    private void printList(ArrayList<Task> list, String description) {
        System.out.println(description + ":");
        for (Task item : list) {
            if (item.getExecutor() == null) {
                System.out.println(item.toString());
            } else {
                System.out.print(item.toString());
                System.out.println(
                        " - EXECUTOR: " + item.getExecutor().getFirstName() + " " + item.getExecutor().getLastName());
            }
        }
        System.out.println("------");
        ;
    }

    /**
     * нераспределенныее задачи будут распределены по менеджерам у которых сейчас нет задач в работе
     * автоматически назначается только 1 задача на свободного менедера в порядкее приоритета задач и времени ее заведения
     * (если необходимо назначить несколько задач - можно сделать это вручную)
     */
    public void autoSetExecutor() {
        for (Employee employee : this.company.getStaff()) {
            if ((employee.getPost() != Posts.CHIEF) && (plannerTasks.getTasksEmployee(employee).isEmpty())) {
                try {
                    plannerTasks.getListWaiting().get(0).setExecutor(employee);
                    //если список пустой и назаначать нечего
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("список нераспределенных задач пуст");
                    return;
                }
            }
        }
    }

    public void exportDataBase(String nameFile) throws IOException {

        try {
            ObjectOutputStream oosEmployee = new ObjectOutputStream(new FileOutputStream(nameFile + "Emp.txt"));
            oosEmployee.writeObject(this.company);

            FileOutputStream fileOutputPlanner = new FileOutputStream(nameFile + "Plan.txt");
            ObjectOutputStream exportPlanner = new ObjectOutputStream(fileOutputPlanner);
            exportPlanner.writeObject(this.plannerTasks);


        } catch (IOException e) {
            System.out.println("ошибка выгрузки: " + e.getMessage());
        }

    }

    public void importDataBase(String nameFile) throws IOException {

        try {
            FileInputStream fileInputEmployee = new FileInputStream(nameFile + "Emp.txt");
            ObjectInputStream importEmployee = new ObjectInputStream(fileInputEmployee);
            this.company = (StaffEmployees) importEmployee.readObject();

            FileInputStream fileInputPlanner = new FileInputStream(nameFile + "Plan.txt");
            ObjectInputStream importPlanner = new ObjectInputStream(fileInputPlanner);
            this.plannerTasks = (PlannerTasks) importPlanner.readObject();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}

