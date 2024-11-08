package lab04;

import lab05.Car;
import lab05.CarBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarBuilderTest {

    @Test
    public void testValidVinCode() {
        Car car = new CarBuilder()
                .setManufacturer("Toyota")
                .setBrand("Corolla")
                .setType("Sedan")
                .setColor("Red")
                .setCostPerDay(50.0)
                .setVinCode("1234567890ABCDEFG")  // Correct VIN code
                .setReleaseYear(2025)
                .build();

        assertNotNull(car);
        assertEquals("Toyota", car.getManufacturer());
        assertEquals("Corolla", car.getBrand());
        assertEquals("Sedan", car.getType());
        assertEquals("Red", car.getColor());
        assertEquals(50.0, car.getCostPerDay());
        assertEquals("1234567890ABCDEFGH/", car.getVinCode());
        assertEquals(2020, car.getReleaseYear());
    }


    @Test
    public void testNullManufacturer() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setManufacturer(null));
        assertEquals("Manufacturer cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyManufacturer() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setManufacturer(""));
        assertEquals("Manufacturer cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testInvalidVinCode() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setVinCode("1234"));
        assertEquals("VIN code must be a valid 17-character alphanumeric code", exception.getMessage());
    }

    @Test
    public void testInvalidReleaseYear() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setReleaseYear(1800));
        assertEquals("Release year must be between 1886 and 2024", exception.getMessage());
    }

    @Test
    public void testNegativeCostPerDay() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setCostPerDay(-1.0));
        assertEquals("Cost per day must be at least 0.0", exception.getMessage());
    }

    @Test
    public void testBuildWithAllValidValues() {
        Car car = new CarBuilder()
                .setManufacturer("Ford")
                .setBrand("Focus")
                .setType("Hatchback")
                .setColor("Blue")
                .setCostPerDay(45.5)
                .setVinCode("ABCDE1234567890FG")
                .setReleaseYear(1700)
                .build();

        assertNotNull(car);
        assertEquals("Ford", car.getManufacturer());
        assertEquals("Focus", car.getBrand());
        assertEquals("Hatchback", car.getType());
        assertEquals("Blue", car.getColor());
        assertEquals(45.5, car.getCostPerDay());
        assertEquals("ABCDE1234567890FG", car.getVinCode());
        assertEquals(1700, car.getReleaseYear());
    }

    @Test
    public void testEmptyBrand() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setBrand(""));
        assertEquals("Brand cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setType(""));
        assertEquals("Type cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyColor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CarBuilder().setColor(""));
        assertEquals("Color cannot be null or empty", exception.getMessage());
    }
}
