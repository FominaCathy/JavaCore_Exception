package gb.Company;

import gb.Company.Company.Departament;
import gb.Company.Company.Service;
import gb.Company.Person.Chief;
import gb.Company.Person.Employee;
import gb.Company.Person.Posts;
import gb.Company.Tasks.Priority;
import gb.Company.Tasks.Task;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        //TODO загрузка начальных данных
        init(service);
        //
        service.printPlanner(); // печать планера
        service.addTask(new Task("digging a well", Priority.LOW));

        service.setExecutorTask(1, "Guk Ivan");
        service.printPlanner(); // печать планера

        service.printAllTaskEmployee("Guk Ivan");
        service.StartTask("Guk Ivan", 1);

        service.printAllTaskEmployee("Guk Ivan");

        service.FinishTask("Guk Ivan", 2);

        service.setExecutorTaskOrderPriority(2, "Guk Ivan");
        service.printAllTaskEmployee("Guk Ivan");

        service.printEmployee();

        service.printListSalary();
        service.changeSalaryAll( 45, 5000);

        service.printListSalary();

        System.out.println("average age: " + service.averageAge());
        System.out.println("average salary: " + service.averageSalary());

        service.increaseSalary(10000);
        service.printListSalary();


    }

    public static void init(Service service) {
        service.addTask(new Task("Task 1", Priority.LOW));
        service.addTask(new Task("Task 2", Priority.MEDIUM));
        service.addTask(new Task("Task 3", Priority.HIGT));

        service.addEmployee(new Employee("Guk", "Ivan", new Date(2005, 06, 07), "1234", Posts.WRITER, 1200));
        service.addEmployee(new Employee("Guk", "Petr", new Date(1985, 10, 02), "4526", Posts.ADMIN, 1500));
        service.addEmployee(new Employee("Bobr", "Lev", new Date(1987, 12, 05), "5687", Posts.ENGINEER, 2000));
        service.addEmployee(new Employee("Lulu", "Yan", new Date(1999, 10, 02), "3698", Posts.ENGINEER, 2300));
        service.addEmployee(new Employee("Grek", "Gleb", new Date(1958, 01, 02), "2665", Posts.WIZARD, 3000));
        service.addEmployee(new Chief("Boss", "BB", new Date(1980, 01, 02), "2675", Departament.CREATOR, 3000));

    }



}
