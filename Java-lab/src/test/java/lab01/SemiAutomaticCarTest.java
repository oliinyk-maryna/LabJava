package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SemiAutomaticCarTest {

    @Test
    void testConstructorAndGetters() {
        SemiAutomaticCar car = new SemiAutomaticCar("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN12345", 2022);

        assertEquals("Toyota", car.getManufacturer());
        assertEquals("Corolla", car.getBrand());
        assertEquals("Sedan", car.getType());
        assertEquals("Red", car.getColor());
        assertEquals(50.0, car.getCostPerDay());
        assertEquals("VIN12345", car.getVinCode());
        assertEquals(2022, car.getReleaseYear());
    }

    @Test
    void testToString() {
        SemiAutomaticCar car = new SemiAutomaticCar("Honda", "Civic", "Hatchback", "Blue", 40.0, "VIN54321", 2021);

        String expected = "SemiAutomaticCar{manufacturer='Honda', brand='Civic', type='Hatchback', color='Blue', costPerDay=40.0, vinCode='VIN54321', releaseYear=2021}";
        assertEquals(expected, car.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        SemiAutomaticCar car1 = new SemiAutomaticCar("BMW", "X5", "SUV", "Black", 100.0, "VIN67890", 2023);
        SemiAutomaticCar car2 = new SemiAutomaticCar("BMW", "X5", "SUV", "Black", 100.0, "VIN67890", 2023);

        assertEquals(car1, car2);

        assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    void testNotEquals() {
        SemiAutomaticCar car1 = new SemiAutomaticCar("Audi", "A4", "Sedan", "Silver", 80.0, "VIN11111", 2020);
        SemiAutomaticCar car2 = new SemiAutomaticCar("Audi", "A6", "Sedan", "Silver", 90.0, "VIN22222", 2020);

        assertNotEquals(car1, car2);
    }
}
