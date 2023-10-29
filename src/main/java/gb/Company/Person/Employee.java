package gb.Company.Person;

import gb.Company.Company.Departament;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends Person {
    private Posts post;
    private double salary;
    protected Departament departament;


    public Employee(String firstName, String lastName, LocalDate birtDay, String phone, Posts post, double salary) {
        super(firstName, lastName, birtDay, phone);
        this.post = post;
        this.salary = salary;
        this.setDepartament();
    }

    public Posts getPost() {
        return post;
    }


    public double getSalary() {
        return salary;
    }


    public Departament getDepartament() {
        return departament;
    }

    private void setSalary(double salary) throws RuntimeException {
        if (salary <= 0) {
            throw new RuntimeException("Нельзя установить з/п = " + salary);
        }
        this.salary = salary;

    }

    //TODO где от должен быть?
    public void changeSalary(int age, double salary) {
        if (this.getAge() > age) {
            this.setSalary(this.getSalary() + salary);
        }
    }

    protected void setDepartament() {
        switch (this.post) {
            case WIZARD -> this.departament = Departament.CREATOR;
            case ADMIN -> this.departament = Departament.ADMIN;
            case ENGINEER -> this.departament = Departament.TEST;
            case WRITER -> this.departament = Departament.CREATOR;

        }
    }

    @Override
    public String toString() {

        String info = "Employee: " + "\n" +
                "name: " + " " + this.firstName + " " + this.lastName + "\n" +
                "age: " + this.getAge() + " yers\n" +
                "post: " + this.post.getTitle() + "\n" +
                "phone: " + this.phone + "\n" +
                "salary: " + this.salary + " rub\n";
        return info;
    }

    public String shortInfo() {

        String info = "Name: " + " " + this.firstName + " " + this.lastName + "\t " +
                "Post: " + this.post.getTitle() + "\t" +
                "Dep.: " + this.getDepartament().getTitle();
        return info;
    }


}
