package gb.Animals;

import java.time.LocalDate;


public abstract class Animal {
    protected String name;
    protected String color;
    protected int birtYear;
    private static final float range = 0.25f;
    double maxDistansRun;
    double maxDistansSwim;
    double maxAltitude;

    Animal(String type, String name, String color, int birtYear,
           double maxDistansRun, double maxDistansSwim, double maxAltitude) {
        this.name = name;
        this.color = color;
        this.birtYear = birtYear;
        this.maxDistansRun = maxDistansRun;
        this.maxDistansSwim = maxDistansSwim;
        this.maxAltitude = maxAltitude;
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        LocalDate date = LocalDate.now();
        return date.getYear() - birtYear;
    }


    public boolean run(double distance) {
        return inRange(distance, maxDistansRun);
    }

    public boolean swim(double distance) {
        return inRange(distance, maxDistansSwim);
    }

    public boolean jump(double altitude) {
        return inRange(altitude, maxAltitude);
    }

    private boolean inRange(double number, double maxNumber) {
        return (number >= maxNumber * (1 - range)) && (number <= maxNumber * (1 + range));
    }
}
