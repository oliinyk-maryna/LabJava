package lab02.services;

import lab02.Car;

import java.util.List;
import java.util.Comparator;

public class CarService {
    /**
     * Finds the car with the highest rental cost per day.
     *
     * @param cars the list of cars
     * @return the car with the highest cost per day, or null if the list is empty
     */
    public Car findCarWithHighestCostPerDay(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparing(Car::getCostPerDay))
                .orElse(null);
    }

    /**
     * Finds the newest car based on release year.
     *
     * @param cars the list of cars
     * @return the newest car, or null if the list is empty
     */
    public Car findNewestCar(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparing(Car::getReleaseYear))
                .orElse(null);
    }

    /**
     * Finds the first car by a specific brand.
     *
     * @param cars the list of cars
     * @param brand the brand to search for
     * @return the first car with the specified brand, or null if not found
     */
    public Car findCarByBrand(List<Car> cars, String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().trim().equalsIgnoreCase(brand.trim()))
                .findFirst()
                .orElse(null);
    }


}
