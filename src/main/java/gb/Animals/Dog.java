package gb.Animals;

public class Dog extends Animal {
    private static final double maxRun = 1500.0;
    private static final double maxSwim = 500.0;
    private static final double maxAltitude = 5.0;


    public Dog(String name, String color, int birtYear) {
        super("Dog", name, color, birtYear, maxRun, maxSwim, maxAltitude);

    }


}
