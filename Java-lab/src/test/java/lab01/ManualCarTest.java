package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManualCarTest {

    @Test
    void testConstructorAndGetters() {
        ManualCar car = new ManualCar("BMW", "3 Series", "Sedan", "White", 70.0, "WBA3B5C50DF587364", 2018);

        assertEquals("BMW", car.getManufacturer());
        assertEquals("3 Series", car.getBrand());
        assertEquals("Sedan", car.getType());
        assertEquals("White", car.getColor());
        assertEquals(70.0, car.getCostPerDay());
        assertEquals("WBA3B5C50DF587364", car.getVinCode());
        assertEquals(2018, car.getReleaseYear());
    }

    @Test
    void testToString() {
        ManualCar car = new ManualCar("Audi", "A4", "Sedan", "Silver", 65.0, "WAUDF68E16A052345", 2019);
        String expected = "ManualCar{" +
                "manufacturer='Audi', " +
                "brand='A4', " +
                "type='Sedan', " +
                "color='Silver', " +
                "costPerDay=65.0, " +
                "vinCode='WAUDF68E16A052345', " +
                "releaseYear=2019" +
                '}';

        assertEquals(expected, car.toString());
    }

    @Test
    void testEquality_sameObject() {
        ManualCar car = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);

        assertEquals(car, car); // Same object should be equal
    }

    @Test
    void testEquality_identicalObjects() {
        ManualCar car1 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);
        ManualCar car2 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);

        assertEquals(car1, car2); // Identical objects should be equal
    }

    @Test
    void testInequality_differentVinCode() {
        ManualCar car1 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);
        ManualCar car2 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556322", 2020);

        assertNotEquals(car1, car2); // Different VIN codes, should not be equal
    }

    @Test
    void testInequality_differentColor() {
        ManualCar car1 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);
        ManualCar car2 = new ManualCar("Honda", "Civic", "Sedan", "Red", 55.0, "2HGFG12668H556321", 2020);

        assertNotEquals(car1, car2); // Different colors, should not be equal
    }

    @Test
    void testHashCode_identicalObjects() {
        ManualCar car1 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);
        ManualCar car2 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);

        assertEquals(car1.hashCode(), car2.hashCode()); // Hash codes should be the same
    }

    @Test
    void testHashCode_differentObjects() {
        ManualCar car1 = new ManualCar("Honda", "Civic", "Sedan", "Black", 55.0, "2HGFG12668H556321", 2020);
        ManualCar car2 = new ManualCar("Toyota", "Corolla", "Sedan", "Blue", 60.0, "1NXBR32E76Z629978", 2021);

        assertNotEquals(car1.hashCode(), car2.hashCode()); // Hash codes should be different
    }
}
