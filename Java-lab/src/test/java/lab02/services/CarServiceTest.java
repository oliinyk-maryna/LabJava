package lab02.services;

import lab02.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private List<Car> cars;
    private CarService carService;

    @BeforeEach
    void setUp() {
        Car car1 = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        Car car2 = new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Honda", "Civic", "Sedan", "Black", 60.0, "VIN789", 2021);
        Car car4 = new Car("BMW", "X5", "SUV", "White", 80.0, "VIN012", 2023);
        Car car5 = new Car("Mazda", "CX-5", "SUV", "Silver", 70.0, "VIN345", 2021);

        cars = Arrays.asList(car1, car2, car3, car4, car5);
        carService = new CarService(cars);
    }

    @Test
    void testFilterByBrandAndManufacturer() {
        List<Car> filteredCars = carService.filterByBrandAndManufacturer("Corolla", "Toyota");

        assertEquals(1, filteredCars.size(), "There should be 1 car with the brand 'Toyota' and model 'Corolla'");
        assertEquals("Toyota", filteredCars.get(0).getManufacturer(), "The filtered car should have brand 'Toyota'");
        assertEquals("Corolla", filteredCars.get(0).getBrand(), "The filtered car should have model 'Corolla'");
    }

    @Test
    void testFindCarWithHighestCostPerDayAndReleaseYearAfter() {
        Car car = carService.findCarWithHighestCostPerDayAndReleaseYearAfter(2020);

        assertNotNull(car, "A car should be found");
        assertEquals(80.0, car.getCostPerDay(), "The car with the highest cost per day should be the BMW X5");
    }


}
