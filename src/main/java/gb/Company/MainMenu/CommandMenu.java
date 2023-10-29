package gb.Company.MainMenu;

import gb.Company.ConsoleUI;

import java.io.IOException;

public abstract class CommandMenu {
    private String description;
    private ConsoleUI consoleUI;


    public CommandMenu(String description, ConsoleUI consoleUI) {
        this.description = description;
        this.consoleUI = consoleUI;
    }

    public String getDescription() {
        return this.description;
    }

    ConsoleUI getConsoleUI() {
        return this.consoleUI;
    }

    public abstract boolean execute() throws IOException;

    public static class Planner extends CommandMenu {


        public Planner(ConsoleUI consoleUI) {
            super("Планнер (вывод на экран)", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().printPlanner();
            return true;
        }

    }

    public static class ListWaiting extends CommandMenu {

        public ListWaiting(ConsoleUI consoleUI) {
            super("Лист задач в ожидании назаначения исполнителя", consoleUI);

        }

        @Override
        public boolean execute() {
            getConsoleUI().printListWaiting();
            return true;
        }
    }

    public static class ListEmployee extends CommandMenu {
        public ListEmployee(ConsoleUI consoleUI) {
            super("Вывести список сотрудников", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().printListEmployee();
            return true;
        }
    }

    public static class Assignment extends CommandMenu {
        public Assignment(ConsoleUI consoleUI) {
            super("Назначить задаче исполнителя", consoleUI);
        }

        @Override
        public boolean execute() {

            getConsoleUI().setExecutorTask();
            return true;
        }
    }

    public static class Exit extends CommandMenu {

        public Exit(ConsoleUI consoleUI) {
            super("Выход", consoleUI);
        }

        @Override
        public boolean execute() throws IOException {
            getConsoleUI().exit();
            System.out.println("Работа закончена. До свидания!");
            return false;
        }
    }

    public static class ImportDataBase extends CommandMenu {

        public ImportDataBase(ConsoleUI consoleUI) {
            super("Загрузка БД из файла", consoleUI);
        }

        @Override
        public boolean execute() throws IOException {
            getConsoleUI().importDataBase();
            return true;
        }
    }

    public static class StartTask extends CommandMenu {
        public StartTask(ConsoleUI consoleUI) {
            super("Начать работу с задачей ", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().startTask();
            return true;
        }
    }

    public static class AutoAssignment extends CommandMenu {
        public AutoAssignment(ConsoleUI consoleUI) {
            super("Распределить новые задачи на свободных сотрудников", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().autoSetExecutor();
            return true;
        }
    }

    public static class AddEmployee extends CommandMenu {
        public AddEmployee(ConsoleUI consoleUI) {
            super("Добавить сотрудника", consoleUI);
        }

        @Override
        public boolean execute() {
            //TODO добавить добавление сотрудника (и начальника)
            getConsoleUI().addEmployee();
            return true;
        }
    }

    public static class CloseTask extends CommandMenu {
        public CloseTask(ConsoleUI consoleUI) {
            super("Закрыть задачу", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().closeTask();
            return true;
        }
    }

    public static class ListTaskEmployee extends CommandMenu {
        public ListTaskEmployee(ConsoleUI consoleUI) {
            super("Вывести список задач сотрудника", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().printWorkTaskEmployee();
            return true;
        }
    }

    public static class ExportDataBase extends CommandMenu {
        public ExportDataBase(ConsoleUI consoleUI) {
            super("Сохранение данных в файл", consoleUI);
        }

        @Override
        public boolean execute() throws IOException {
            getConsoleUI().exportDataBase();
            return true;
        }
    }

    public static class AddTask extends CommandMenu {
        public AddTask(ConsoleUI consoleUI) {
            super("Добавить задачу", consoleUI);
        }

        @Override
        public boolean execute() {
            getConsoleUI().addTask();
            return true;
        }
    }
}

