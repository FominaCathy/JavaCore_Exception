package gb.Company.Company;

import gb.Company.Person.Employee;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * штат компании. Хранит список всех сотрудников
 */
public class StaffEmployees implements Serializable {
    private ArrayList<Employee> staff;

    public StaffEmployees(){
        this.staff = new ArrayList<>();
    }

    public ArrayList<Employee> getStaff() {
        return staff;
    }

    public void addEmployee(Employee employee){
        staff.add(employee);
    }

    /**
     * получить сотрудника по имени и фамилии из штата
     * @param fullName
     * @return
     */
    public Employee getForName(String fullName) {
        try {
            String firstName = fullName.split(" ")[0];
            String lastName = fullName.split(" ")[1];

            for (Employee employee : staff) {
                if ((employee.getFirstName().equals(firstName)) && (employee.getLastName().equals(lastName))) {
                    return employee;
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("необходимо указать имя и фамилию сотрудника");
            //TODO logger
        }
        System.out.println("Сотрудник с таким именем не найден");
        return null;
    }
}
