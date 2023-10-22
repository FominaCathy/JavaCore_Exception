package gb.Company.Company;

import gb.Company.Person.Employee;
import java.util.ArrayList;


public class StaffEmployees {
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

    public Employee getForName(String fullName) {
        String firstName = fullName.split(" ")[0];
        String lastName = fullName.split(" ")[1];

        for (Employee employee : staff) {
            if ((employee.getFirstName().equals(firstName)) && (employee.getLastName().equals(lastName))){
                return employee;
            }
        }
        return null;
    }
}
