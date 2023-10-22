package gb.Company.Person;

public enum Posts {
    WRITER ("writer"),
    ENGINEER ("engineer"),
    ADMIN ("admin"),
    WIZARD ("wizard"),
    CHIEF ("Chief of departament");

    private String title;

    Posts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
