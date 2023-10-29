package gb.Company.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Person implements Serializable {

    protected String firstName;
    protected String lastName;
    protected String phone;
    protected LocalDate birtDay;

    public Person(String firstName, String lastName, LocalDate birtDay, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birtDay = birtDay;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getPhone() {
        return phone;
    }


    public int getAge() {
        return (LocalDate.now().getYear() - birtDay.getYear());
    }


    @Override
    public String toString() {

        String info = "Employee: " + "\n" +
                "name: " + " " + this.firstName + " " + this.lastName + "\n" +
                "age: " + this.getAge() + " yers\n" +
                "phone: " + this.phone + "\n";
        return info;
    }
}
