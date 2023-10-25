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
        // загрузка начальных данных (сотрудники и задачи)
        init(service);

//        service.printPlanner(); // печать планера
//        service.printListWaiting(); // печать листа ожидания
        service.setExecutorTask(1, "Guk Ivan");
        service.StartTask("Guk Ivan", 1);
//
        service.setExecutorTask(2, "Guk Ivan");
        service.StartTask("Guk Ivan", 2);
//
//        service.CloseTask("Guk Ivan", 2);
//        service.printWorkTaskEmployee("Guk Ivan");

        service.printPlanner(); // печать планера
        service.printListWaiting(); // печать листа ожидания

//        service.printListSalary();
//        service.changeSalaryAll( 45, 5000); // изменение з/п всем сотрудникам, старше указанного возраста
//
//        service.printListSalary();
////
//        System.out.println("\naverage age: " + service.averageAge()); // средний возраст компаниии
//        System.out.println("\naverage salary: " + service.averageSalary());// средняя з.п компании
////
//        service.increaseSalary(10000); // повышение з/п всем кроме руководителей
//        service.printListSalary();


        //автоматическое распределение задач. автоматически назначается только 1 задача на свободного менедера в порядке приоритета
        service.autoSetExecutor();
        service.setExecutorTask(3, "Lulu Yan");
        service.printPlanner();
        service.printListWaiting(); //очередь свободных задач
    }

    public static void init(Service service) {
        service.addTask(new Task("Task 1", Priority.LOW));
        service.addTask(new Task("Task 2", Priority.MEDIUM));
        service.addTask(new Task("Task 5", Priority.LOW));
        service.addTask(new Task("Task 3", Priority.HIGT));
        service.addTask(new Task("Task 4", Priority.MEDIUM));

        // спиок сотрудников компании
        service.addEmployee(new Employee("Guk", "Ivan", new Date(2005, 06, 07), "1234", Posts.WRITER, 1200));
        service.addEmployee(new Employee("Lulu", "Yan", new Date(1985, 10, 02), "4526", Posts.ADMIN, 1500));
        service.addEmployee(new Employee("Bobr", "Lev", new Date(1987, 12, 05), "5687", Posts.ENGINEER, 2000));
        service.addEmployee(new Chief("Boss", "BB", new Date(1980, 01, 02), "2675", Departament.CREATOR, 3000));

    }


}
