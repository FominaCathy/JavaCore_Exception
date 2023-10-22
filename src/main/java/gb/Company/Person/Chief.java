package gb.Company.Person;

import gb.Company.Company.Departament;

import java.util.Date;

public class Chief extends Employee {


    public Chief(String firstName, String lastName, Date birtDay, String phone, Departament departament, double salary) {
        super(firstName, lastName, birtDay, phone, Posts.CHIEF, salary);
        this.departament = departament;
    }



}
