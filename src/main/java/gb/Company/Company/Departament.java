package gb.Company.Company;

public enum Departament {
    CREATOR ("Departament creators"),
    ADMIN ("Departament tecnique"),
    TEST ("Departament testing");

    private String title;

    Departament(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
