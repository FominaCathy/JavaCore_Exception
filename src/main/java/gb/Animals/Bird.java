package gb.Animals;

public class Bird extends Animal {
    private static final double maxRun = 10.0;
    private static final double maxSwim = 0.0;
    private static final double maxAltitude = 0.0;




    public Bird(String name, String color, int birtYear) {
        super("Cat", name, color, birtYear, maxRun, maxSwim, maxAltitude);

    }

    @Override
    public boolean swim(double distance) {
        return false;
    }

    @Override
    public boolean jump(double altitude) {
        return false;
    }
}
