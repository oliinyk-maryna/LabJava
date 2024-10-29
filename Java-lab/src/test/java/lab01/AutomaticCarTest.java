package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutomaticCarTest {

    @Test
    void testConstructorAndGetters() {
        AutomaticCar car = new AutomaticCar("Toyota", "Camry", "Sedan", "Black", 50.0, "JT2BG12K9T0049181", 2020);

        assertEquals("Toyota", car.getManufacturer());
        assertEquals("Camry", car.getBrand());
        assertEquals("Sedan", car.getType());
        assertEquals("Black", car.getColor());
        assertEquals(50.0, car.getCostPerDay());
        assertEquals("JT2BG12K9T0049181", car.getVinCode());
        assertEquals(2020, car.getReleaseYear());
    }

    @Test
    void testToString() {
        AutomaticCar car = new AutomaticCar("Honda", "Civic", "Sedan", "Blue", 45.0, "1HGBH41JXMN109186", 2019);
        String expected = "AutomaticCar{" +
                "manufacturer='Honda', " +
                "brand='Civic', " +
                "type='Sedan', " +
                "color='Blue', " +
                "costPerDay=45.0, " +
                "vinCode='1HGBH41JXMN109186', " +
                "releaseYear=2019" +
                '}';

        assertEquals(expected, car.toString());
    }

    @Test
    void testEquality_sameObject() {
        AutomaticCar car = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);

        assertEquals(car, car); // Same object should be equal
    }

    @Test
    void testEquality_identicalObjects() {
        AutomaticCar car1 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);
        AutomaticCar car2 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);

        assertEquals(car1, car2); // Identical objects should be equal
    }

    @Test
    void testInequality_differentVinCode() {
        AutomaticCar car1 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);
        AutomaticCar car2 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120510", 2021);

        assertNotEquals(car1, car2); // Different VIN codes, should not be equal
    }

    @Test
    void testInequality_differentColor() {
        AutomaticCar car1 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);
        AutomaticCar car2 = new AutomaticCar("Ford", "Focus", "Hatchback", "Blue", 60.0, "3FADP4EJ2DM120509", 2021);

        assertNotEquals(car1, car2); // Different colors, should not be equal
    }

    @Test
    void testHashCode_identicalObjects() {
        AutomaticCar car1 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);
        AutomaticCar car2 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);

        assertEquals(car1.hashCode(), car2.hashCode()); // Hash codes should be the same
    }

    @Test
    void testHashCode_differentObjects() {
        AutomaticCar car1 = new AutomaticCar("Ford", "Focus", "Hatchback", "Red", 60.0, "3FADP4EJ2DM120509", 2021);
        AutomaticCar car2 = new AutomaticCar("Toyota", "Camry", "Sedan", "Black", 55.0, "JT2BG12K9T0049181", 2020);

        assertNotEquals(car1.hashCode(), car2.hashCode()); // Hash codes should be different
    }
}
