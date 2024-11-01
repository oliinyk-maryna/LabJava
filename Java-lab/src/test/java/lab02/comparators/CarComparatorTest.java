package lab02.comparators;

import lab02.Car;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarComparatorTest {

    @Test
    void testByReleaseYear() {
        Car car1 = new Car("Toyota", "Camry", "Sedan", "Red", 50.0, "VIN123", 2018);
        Car car2 = new Car("Honda", "Accord", "Sedan", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Ford", "Focus", "Hatchback", "Green", 55.0, "VIN789", 2017);

        List<Car> cars = Arrays.asList(car1, car2, car3);
        cars.sort(CarComparator.byReleaseYear());

        assertEquals(car3, cars.get(0));
        assertEquals(car1, cars.get(1));
        assertEquals(car2, cars.get(2));
    }

    @Test
    void testByCostPerDay() {
        Car car1 = new Car("Toyota", "Camry", "Sedan", "Red", 50.0, "VIN123", 2018);
        Car car2 = new Car("Honda", "Accord", "Sedan", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Ford", "Focus", "Hatchback", "Green", 50.0, "VIN789", 2017);

        List<Car> cars = Arrays.asList(car1, car2, car3);
        cars.sort(CarComparator.byCostPerDay());

        assertEquals(car2, cars.get(0)); // Cheapest car
        assertEquals(car3, cars.get(1)); // Same cost as car1 but older release year
        assertEquals(car1, cars.get(2)); // Most expensive or latest same-cost car
    }

    @Test
    void testByBrand() {
        Car car1 = new Car("Toyota", "Camry", "Sedan", "Red", 50.0, "VIN123", 2018);
        Car car2 = new Car("Honda", "Accord", "Sedan", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Ford", "Focus", "Hatchback", "Green", 55.0, "VIN789", 2017);

        List<Car> cars = Arrays.asList(car1, car2, car3);
        cars.sort(CarComparator.byBrand());

        assertEquals(car2, cars.get(0)); // Accord (brand)
        assertEquals(car1, cars.get(1)); // Camry (brand)
        assertEquals(car3, cars.get(2)); // Focus (brand)
    }

    @Test
    void testByManufacturer() {
        Car car1 = new Car("Toyota", "Camry", "Sedan", "Red", 50.0, "VIN123", 2018);
        Car car2 = new Car("Ford", "Focus", "Hatchback", "Green", 55.0, "VIN456", 2020);
        Car car3 = new Car("Ford", "Fiesta", "Hatchback", "Blue", 45.0, "VIN789", 2019);

        List<Car> cars = Arrays.asList(car1, car2, car3);
        cars.sort(CarComparator.byManufacturer());

        // Since "Ford Fiesta" comes before "Ford Focus" by brand name, update the assertions
        assertEquals(car3, cars.get(0)); // Ford (manufacturer), Fiesta (brand)
        assertEquals(car2, cars.get(1)); // Ford (manufacturer), Focus (brand)
        assertEquals(car1, cars.get(2)); // Toyota (manufacturer)
    }

}
