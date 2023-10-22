package Animals;

import gb.Animals.Animal;
import gb.Animals.Cat;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestCat {

    Animal catMursik = new Cat("Mursik", "black", 2000);
    Cat catBarsik = new Cat("Barsik", "white", 2010);


    @Test
    void createCat() {
        Assertions.assertEquals("Mursik", catMursik.getName());
        Assertions.assertEquals("Barsik", catBarsik.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {500*0.75, 500, 500*1.25})
    void distanceValidRunCat(double arg) {
        Assertions.assertTrue(catBarsik.run(arg));
    }

    @ParameterizedTest
    @ValueSource(doubles = {500*0.74, 500*1.26})
    void distanceInvalidRunCat(double arg) {
        Assertions.assertFalse(catBarsik.run(arg));
    }

    @ParameterizedTest
    @ValueSource(doubles = {10*0.75, 10, 10*1.25})
    void distanceValidJumpCat(double arg) {
        Assertions.assertTrue(catBarsik.jump(arg));

    }

    @ParameterizedTest
    @ValueSource(doubles = {10*0.74, 10*1.26})
    void distanceInvalidJumpCat(double arg) {
        Assertions.assertFalse(catBarsik.jump(arg));

    }
}
