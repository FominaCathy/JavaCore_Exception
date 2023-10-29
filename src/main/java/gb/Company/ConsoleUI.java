package gb.Company;


import gb.Company.Company.Departament;
import gb.Company.Company.Service;
import gb.Company.Person.Chief;
import gb.Company.Person.Employee;
import gb.Company.Person.Posts;
import gb.Company.Tasks.Priority;
import gb.Company.Tasks.Task;

import java.io.IOException;
;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private Presentor presentor;
    private Menu mainMenu;
    private boolean work;

    public ConsoleUI() {
        this.mainMenu = new Menu(this);
        this.work = true;
        this.presentor = new Presentor();
    }

    public void start() throws IOException {
        int intChoice = 0;
//        init(presentor.getService());
        while (work) {
            System.out.println(mainMenu.printMenu());
            System.out.println("выберите пункт меню:");
            Scanner scanner = new Scanner(System.in);

            String stringChoice = scanner.nextLine();
            if (stringChoice.matches("[0-9]+")) {
                intChoice = Integer.parseInt(stringChoice);
                if (intChoice > mainMenu.getSize()) {
                    System.out.println("такого пункта нет в меню. попробуйте еще раз");
                } else {
                    work = mainMenu.choice(intChoice - 1);
                }
            } else {
                System.out.println("введеное значение должно быть числовым");
            }
        }
    }

    public void printPlanner() {
        presentor.printPlanner();
    }

    public void printListWaiting() {
        presentor.ListWaiting();
    }

    public int getTask() {
        String stringID = null;
        int intID = 0;

        while (intID == 0) {
            System.out.println("укажите ID задачи:");
            stringID = scanner.nextLine();
            if (stringID.matches("[0-9]+")) {
                intID = Integer.parseInt(stringID);
            } else
                System.out.println("id задачи должен быть числовым");
        }
        return intID;
    }

    public String getFullName() {
        String fullName = null;

        boolean flag = true;
        while (flag) {
            System.out.println("укажите полное имя:");
            fullName = scanner.nextLine();
            if (fullName.matches("[a-zA-Z]+\\s[a-zA-Z]+")) {
                flag = false;
            } else
                System.out.println("неверное ввели имя. попробуйте еще раз. ");
        }

        return fullName;
    }
//    //TODO убрать
    public static void init(Service service) {
        service.addTask(new Task("Task 1", Priority.LOW));
        service.addTask(new Task("Task 2", Priority.MEDIUM));
        service.addTask(new Task("Task 5", Priority.LOW));
        service.addTask(new Task("Task 3", Priority.HIGT));
        service.addTask(new Task("Task 4", Priority.MEDIUM));

        // спиок сотрудников компании
        service.addEmployee(new Employee("Guk", "Ivan", LocalDate.of(2005, 06, 07), "1234", Posts.WRITER, 1200));
        service.addEmployee(new Employee("Lulu", "Yan", LocalDate.of(1985, 10, 02), "4526", Posts.ADMIN, 1500));
        service.addEmployee(new Employee("Bobr", "Lev", LocalDate.of(1987, 12, 05), "5687", Posts.ENGINEER, 2000));
        service.addEmployee(new Chief("Boss", "BB", LocalDate.of(1980, 01, 02), "2675", Departament.CREATOR, 3000));

    }

    private static Task createTask() {
        int numPriority = -1;
        System.out.println("Введите описание задачи: ");
        String descr = scanner.nextLine();
        boolean flag = false;
        while (!flag) {
            System.out.println("укажите приоритет (его номер): ");
            for (int i = 0; i < Priority.values().length; i++) {
                System.out.println(i + " - " + Priority.values()[i]);
            }
            String prior = scanner.nextLine();
            if (prior.matches("[0-9]+") && Integer.parseInt(prior) < Priority.values().length) {
                numPriority = Integer.parseInt(prior);
                flag = true;
            } else {
                System.out.println("попробуй еще раз");
            }
        }
        return new Task(descr, Priority.values()[numPriority]);
    }

    public void addTask() {
        Task newTask = createTask();
        presentor.addTask(newTask);
    }

    public void importDataBase() throws IOException {
        System.out.println("Введите название архива, из которого восстановить данные: ");
        String nameFile = scanner.nextLine();

        presentor.importDataBase(nameFile);
    }

    public void setExecutorTask() {
        int idTask = getTask();
        String fullName = getFullName();
        presentor.setExecutorTask(idTask, fullName);
    }

    public void printListEmployee() {
        presentor.printListEmployee();
    }

    public void printWorkTaskEmployee() {
        System.out.println("Введите полное имя сотрудника: ");
        String fullName = getFullName();
        presentor.printWorkTaskEmployee(fullName);
    }

    public void exportDataBase() throws IOException {
        System.out.println("Введите название архива, куда сохранить данные: ");
        String nameFile = scanner.nextLine();
        presentor.exportDataBase(nameFile);
    }

    public void startTask() {
        System.out.println("Для назначения статуса \"В работе\" необходимо указать инициатора и номер задачи");
        int idTask = getTask();
        String fullName = getFullName();
        presentor.startTask(fullName, idTask);
    }

    public void closeTask() {
        System.out.println("Для назначения статуса \"Закрыт\" необходимо указать инициатора и номер задачи");
        int idTask = getTask();
        String fullName = getFullName();
        presentor.closeTask(fullName, idTask);
    }

    public void autoSetExecutor() {
        presentor.autoSetExecutor();
    }

    public void exit() throws IOException {
        System.out.println("Сохранить данные? (y/n д/н)");
        String save = scanner.nextLine();
        boolean flagExit = false;
        while (!flagExit) {
            if (save.matches("[yYдД]")) {
                exportDataBase();
                flagExit = true;
            } else if (save.matches("[nNнН]")) {

                flagExit = true;
            } else {
                System.out.println("Сохранить данные? (y/n д/н)");
            }
        }
    }

    public void addEmployee() {
        String fullName = getFullName();
        LocalDate birtDay = inputBirtDay();
        String phone = inputPhone();
        double salary = inputSalary();
        Posts post = inputPost();
        if (post == Posts.CHIEF) {
            Departament departament = inputDepartament();
            presentor.addEmployee(
                    new Chief(fullName.split(" ")[0], fullName.split(" ")[1], birtDay, phone, departament, salary));
        } else {
            presentor.addEmployee(
                    new Employee(fullName.split(" ")[0], fullName.split(" ")[1], birtDay, phone, post, salary));
        }
    }

    private Departament inputDepartament() {
        boolean flag = false;
        int numDep = 0;
        String dep = "";
        while (!flag) {
            System.out.println("укажите департамент (его номер): ");
            for (int i = 0; i < Departament.values().length; i++) {
                System.out.println(i + " - " + Departament.values()[i]);
            }
            dep = scanner.nextLine();
            if (dep.matches("[0-9]+") && Integer.parseInt(dep) < Departament.values().length) {
                numDep = Integer.parseInt(dep);
                flag = true;
            } else {
                System.out.println("попробуй еще раз");
            }
        }
        return Departament.values()[numDep];
    }

    private Posts inputPost() {
        boolean flag = false;
        int numPost = 0;
        String post = "";
        while (!flag) {
            System.out.println("укажите должность (его номер): ");
            for (int i = 0; i < Posts.values().length; i++) {
                System.out.println(i + " - " + Posts.values()[i]);
            }
            post = scanner.nextLine();
            if (post.matches("[0-9]+") && Integer.parseInt(post) < Posts.values().length) {
                numPost = Integer.parseInt(post);
                flag = true;
            } else {
                System.out.println("попробуй еще раз");
            }
        }
        return Posts.values()[numPost];

    }

    private double inputSalary() {
        String stringSalary;
        double salary = 0;
        boolean flag = false;
        while (!flag) {
            System.out.println("введите з/п: ");
            stringSalary = scanner.nextLine();

            try {
                salary = Double.parseDouble(stringSalary);
                flag = true;
            } catch (Exception e) {
                System.out.println("неверная з/п. попробуйте еще раз. ");
            }
        }
        return salary;
    }

    private String inputPhone() {
        String phone = null;

        boolean flag = true;
        while (flag) {
            System.out.println("укажите телефон (только цифры):");
            phone = scanner.nextLine();
            if (phone.matches("[0-9]+")) {
                flag = false;
            } else
                System.out.println("неверное ввели телефон. попробуйте еще раз. ");
        }

        return phone;
    }

    private LocalDate inputBirtDay() {
        LocalDate birtDay = null;
        String stringDay;

        boolean flag = false;
        while (!flag) {
            System.out.println("введите дату рождения в формате (ГГГГ-ММ-ДД): ");
            stringDay = scanner.nextLine();

            try {
                birtDay = LocalDate.parse(stringDay);
                flag = true;
            } catch (Exception e) {
                System.out.println("неверная дата. попробуйте еще раз. ");
            }
        }
        return birtDay;
    }
}
