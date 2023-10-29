package gb.Company.Person;

import gb.Company.Company.Departament;

import java.time.LocalDate;
import java.util.Date;

public class Chief extends Employee {


    public Chief(String firstName, String lastName, LocalDate birtDay, String phone, Departament departament, double salary) {
        super(firstName, lastName, birtDay, phone, Posts.CHIEF, salary);
        this.departament = departament;
    }



}
