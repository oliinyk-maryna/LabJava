package lab01;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testCarConstructorAndGetters() {
        Car car = new Car("Toyota", "Corolla", "Sedan", "White", 40.0, "1HGCM82633A123456", 2020);

        assertEquals("Toyota", car.getManufacturer());
        assertEquals("Corolla", car.getBrand());
        assertEquals("Sedan", car.getType());
        assertEquals("White", car.getColor());
        assertEquals(40.0, car.getCostPerDay());
        assertEquals("1HGCM82633A123456", car.getVinCode());
        assertEquals(2020, car.getReleaseYear());

        assertNotNull(car.getId());
        assertNotNull(car.getCreatedAt());
    }

    @Test
    void testCarToString() {
        Car car = new Car("Honda", "Civic", "Hatchback", "Red", 50.0, "1HGCM82633A654321", 2019);

        String expected = "Car{" +
                "manufacturer='Honda'" +
                ", brand='Civic'" +
                ", type='Hatchback'" +
                ", color='Red'" +
                ", costPerDay=50.0" +
                ", vinCode='1HGCM82633A654321'" +
                ", releaseYear=2019" +
                '}';

        assertEquals(expected, car.toString());
    }

    @Test
    void testCarEqualsAndHashCode() {
        Car car1 = new Car("Ford", "Mustang", "Coupe", "Blue", 60.0, "1HGCM82633A000001", 2021);
        Car car2 = new Car("Ford", "Mustang", "Coupe", "Blue", 60.0, "1HGCM82633A000001", 2021);

        // Check if two Car objects with the same attributes are equal
        assertEquals(car1, car2);

        // Check if the hashCodes are the same for equal objects
        assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    void testCarNotEquals() {
        Car car1 = new Car("BMW", "M3", "Coupe", "Black", 100.0, "1HGCM82633A000002", 2021);
        Car car2 = new Car("Audi", "A4", "Sedan", "Black", 90.0, "1HGCM82633A000003", 2021);

        // Check that two different Car objects are not equal
        assertNotEquals(car1, car2);
    }

    @Test
    void testSetters() {
        Car car = new Car("Nissan", "Altima", "Sedan", "Grey", 45.0, "1HGCM82633A000004", 2020);

        // Set new values
        car.setManufacturer("Mazda");
        car.setBrand("3");
        car.setType("Hatchback");
        car.setColor("Blue");
        car.setCostPerDay(55.0);
        car.setVinCode("1HGCM82633A000005");
        car.setReleaseYear(2022);

        // Verify that the setters work correctly
        assertEquals("Mazda", car.getManufacturer());
        assertEquals("3", car.getBrand());
        assertEquals("Hatchback", car.getType());
        assertEquals("Blue", car.getColor());
        assertEquals(55.0, car.getCostPerDay());
        assertEquals("1HGCM82633A000005", car.getVinCode());
        assertEquals(2022, car.getReleaseYear());
    }
}
