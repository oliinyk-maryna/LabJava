package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CarBuilder class.
 */
public class CarBuilderTest {

    @Test
    public void testSetManufacturer() {
        CarBuilder builder = new CarBuilder();
        builder.setManufacturer("Toyota");
        Car car = builder.build();
        assertEquals("Toyota", car.getManufacturer(), "Manufacturer should be 'Toyota'");
    }

    @Test
    public void testSetBrand() {
        CarBuilder builder = new CarBuilder();
        builder.setBrand("Camry");
        Car car = builder.build();
        assertEquals("Camry", car.getBrand(), "Brand should be 'Camry'");
    }

    @Test
    public void testSetType() {
        CarBuilder builder = new CarBuilder();
        builder.setType("Sedan");
        Car car = builder.build();
        assertEquals("Sedan", car.getType(), "Type should be 'Sedan'");
    }

    @Test
    public void testSetColor() {
        CarBuilder builder = new CarBuilder();
        builder.setColor("Red");
        Car car = builder.build();
        assertEquals("Red", car.getColor(), "Color should be 'Red'");
    }

    @Test
    public void testSetCostPerDay() {
        CarBuilder builder = new CarBuilder();
        builder.setCostPerDay(55.99);
        Car car = builder.build();
        assertEquals(55.99, car.getCostPerDay(), 0.001, "Cost per day should be 55.99");
    }

    @Test
    public void testSetVinCode() {
        CarBuilder builder = new CarBuilder();
        builder.setVinCode("1HGCM82633A123456");
        Car car = builder.build();
        assertEquals("1HGCM82633A123456", car.getVinCode(), "VIN code should be '1HGCM82633A123456'");
    }

    @Test
    public void testSetReleaseYear() {
        CarBuilder builder = new CarBuilder();
        builder.setReleaseYear(2020);
        Car car = builder.build();
        assertEquals(2020, car.getReleaseYear(), "Release year should be 2020");
    }

    @Test
    public void testBuildCarWithAllProperties() {
        CarBuilder builder = new CarBuilder();
        Car car = builder.setManufacturer("Toyota")
                .setBrand("Camry")
                .setType("Sedan")
                .setColor("Red")
                .setCostPerDay(55.99)
                .setVinCode("1HGCM82633A123456")
                .setReleaseYear(2020)
                .build();

        assertAll("Car properties",
                () -> assertEquals("Toyota", car.getManufacturer()),
                () -> assertEquals("Camry", car.getBrand()),
                () -> assertEquals("Sedan", car.getType()),
                () -> assertEquals("Red", car.getColor()),
                () -> assertEquals(55.99, car.getCostPerDay(), 0.001),
                () -> assertEquals("1HGCM82633A123456", car.getVinCode()),
                () -> assertEquals(2020, car.getReleaseYear())
        );
    }
}
