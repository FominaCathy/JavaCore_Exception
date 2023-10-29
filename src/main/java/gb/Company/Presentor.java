package gb.Company;

import gb.Company.Company.Service;
import gb.Company.Person.Chief;
import gb.Company.Person.Employee;
import gb.Company.Tasks.Task;

import java.io.IOException;

public class Presentor {
    private Service service;

    public Presentor() {
        this.service = new Service();
    }

    public void printPlanner() {
        service.printPlanner();
    }

    public void ListWaiting() {
        service.printListWaiting();
    }

    public void printListEmployee() {
        service.printListEmployee();
    }

    public void setExecutorTask(int idTask, String fullName) {
        service.setExecutorTask(idTask, fullName);
    }

    public void printWorkTaskEmployee(String fullName) {
        service.printWorkTaskEmployee(fullName);
    }

    public void exportDataBase(String nameFile) throws IOException {
        service.exportDataBase(nameFile);
    }

    public void importDataBase(String nameFile) throws IOException {
        service.importDataBase(nameFile);
    }

    public void addTask(Task newTask) {
        service.addTask(newTask);
    }

    public void startTask(String fullName, int idTask) {
        service.startTask(fullName, idTask);
    }

    public void closeTask(String fullName, int idTask) {
        service.closeTask(fullName, idTask);
    }

    public void autoSetExecutor() {
        service.autoSetExecutor();
    }

    public void addEmployee(Employee employee) {
        service.addEmployee(employee);
    }

    public Service getService() {
        return service;
    }
}
