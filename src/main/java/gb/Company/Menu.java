package gb.Company;

import gb.Company.MainMenu.CommandMenu;


import java.io.IOException;
import java.util.ArrayList;


public class Menu {

    private static ArrayList<CommandMenu> listMenu;
//    public static Service service = new Service();

    public Menu(ConsoleUI consoleUI) {
        listMenu = new ArrayList<>();
        listMenu.add(new CommandMenu.Planner(consoleUI)); //печать планнера
        listMenu.add(new CommandMenu.ListWaiting(consoleUI)); // печать листа ожидания
        listMenu.add(new CommandMenu.ListEmployee(consoleUI));//вывести список сотрудников
        listMenu.add(new CommandMenu.ListTaskEmployee(consoleUI)); //вывести список задач сотрудника
        listMenu.add(new CommandMenu.AddTask(consoleUI));//добавить задачу в список
        listMenu.add(new CommandMenu.Assignment(consoleUI)); //назначение исполнителя задаче
        listMenu.add(new CommandMenu.StartTask(consoleUI)); // стартовать задачу
        listMenu.add(new CommandMenu.CloseTask(consoleUI)); // закрыть задачу
        listMenu.add(new CommandMenu.AutoAssignment(consoleUI)); //автораспределение задач
        listMenu.add(new CommandMenu.AddEmployee(consoleUI)); //добавить сотрудника
        listMenu.add(new CommandMenu.ExportDataBase(consoleUI));//сохранить данные в файл
        listMenu.add(new CommandMenu.ImportDataBase(consoleUI));//загрузить данные из файла
        listMenu.add(new CommandMenu.Exit(consoleUI)); // закончить работу

    }

    public String printMenu() {

        StringBuilder stringMenu = new StringBuilder();
        stringMenu.append("Меню:\n");

        for (int i = 0; i < listMenu.size(); i++) {
            stringMenu.append((i + 1));
            stringMenu.append(".\t");
            stringMenu.append(listMenu.get(i).getDescription());
            stringMenu.append("\n");
        }
        return stringMenu.toString();
    }

    public static int getSize() {
        return listMenu.size();
    }

    public static boolean choice(int choice) throws IOException {
        return listMenu.get(choice).execute();
    }

}
