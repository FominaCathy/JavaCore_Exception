package gb.Company.Person;

import java.time.LocalDate;
import java.util.Date;

public class Person {

    protected String firstName;
    protected String lastName;
    protected String phone;
    protected Date birtDay;

    public Person(String firstName, String lastName, Date birtDay, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        //TODO проверка даты
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
