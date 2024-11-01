package lab02.services;

import lab02.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        carService = new CarService();

        // Sample Car instances
        Car car1 = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        Car car2 = new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Honda", "Civic", "Sedan", "Black", 60.0, "VIN789", 2021);
        Car car4 = new Car("BMW", "X5", "SUV", "White", 80.0, "VIN012", 2023);
        Car car5 = new Car("Mazda", "CX-5", "SUV", "Silver", 70.0, "VIN345", 2021);

        cars = Arrays.asList(car1, car2, car3, car4, car5); // Initialize the list with cars
    }

    @Test
    void testFindCarWithHighestCostPerDay() {
        Car highestCostCar = carService.findCarWithHighestCostPerDay(cars);

        assertNotNull(highestCostCar, "Expected a car to be found but got null.");
        assertEquals("BMW", highestCostCar.getManufacturer(), "Expected the car with the highest cost per day to be BMW.");
        assertEquals(80.0, highestCostCar.getCostPerDay(), "Expected the cost per day to be 80.0.");
    }

    @Test
    void testFindCarWithHighestCostPerDay_EmptyList() {
        List<Car> emptyCars = Arrays.asList();
        Car highestCostCar = carService.findCarWithHighestCostPerDay(emptyCars);
        assertNull(highestCostCar, "Expected null when no cars are available.");
    }

    @Test
    void testFindNewestCar() {
        Car newestCar = carService.findNewestCar(cars);

        assertNotNull(newestCar, "Expected a car to be found but got null.");
        assertEquals("X5", newestCar.getBrand(), "Expected the newest car brand to be BMW.");
        assertEquals("SUV", newestCar.getType(), "Expected the type of the newest car to be SUV.");
        assertEquals(2023, newestCar.getReleaseYear(), "Expected the release year to be 2023.");
    }


    @Test
    void testFindNewestCar_EmptyList() {
        List<Car> emptyCars = Arrays.asList();
        Car newestCar = carService.findNewestCar(emptyCars);
        System.out.println("Newest car: " + newestCar);  // This should print "null"
        assertNull(newestCar, "Expected null when no cars are available.");
    }

    @Test
    void testFindCarByBrand() {
        // Diagnostic: Print all brands in the cars list to check for exact matches
        cars.forEach(car -> System.out.println("Car brand: " + car.getBrand()));

        Car foundCar = carService.findCarByBrand(cars, "Focus");
        assertEquals("Focus", foundCar.getBrand(), "The brand of the found car is not as expected.");
    }

    @Test
    void testFindCarByBrand_NotFound() {
        Car foundCar = carService.findCarByBrand(cars, "Nonexistent");

        assertNull(foundCar, "Expected null when searching for a car that does not exist.");
    }
}
