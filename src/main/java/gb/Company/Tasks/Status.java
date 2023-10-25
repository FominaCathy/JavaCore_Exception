package gb.Company.Tasks;

public enum Status {
    NEW ("Новый"),
    PROGRESS ("В работе"),
    ONHOLD ("Приостановлен"),
    CLOSE ("Закрыт");

    private final String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
