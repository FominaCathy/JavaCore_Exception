package gb.Animals;

public class Cat extends Animal {
    private static final double maxRun = 500.0;
    private static final double maxSwim = 0.0;
    private static final double maxAltitude = 10.0;


    private static int id = 0;

    private int idCat;

    public Cat(String name, String color, int birtYear) {
        super("Cat", name, color, birtYear, maxRun, maxSwim, maxAltitude);
        this.idCat = ++id;
    }

    public int getIdCat() {
        return this.idCat;
    }

    @Override
    public boolean swim(double distance) {
        return false;
    }
}
