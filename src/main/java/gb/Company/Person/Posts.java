package gb.Company.Person;

public enum Posts {
    WRITER ("Writer"),
    ENGINEER ("Engineer"),
    ADMIN ("Admin"),
    WIZARD ("Wizard"),
    CHIEF ("Chief");

    private String title;

    Posts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
