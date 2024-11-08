package lab04.services;

import lab05.Car;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CarService provides methods for operations on collections of Car objects.
 */
public class CarService {

    private List<Car> cars;

    public CarService(List<Car> cars) {
        this.cars = cars;
    }

    // Фільтрування за брендом і виробником
    public List<Car> filterByBrandAndManufacturer(String brand, String manufacturer) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    // Пошук автомобіля з найвищою вартістю оренди за день і роком випуску після заданого
    public Car findCarWithHighestCostPerDayAndReleaseYearAfter(int releaseYear) {
        return cars.stream()
                .filter(car -> car.getReleaseYear() > releaseYear)
                .max(Comparator.comparing(Car::getCostPerDay))
                .orElse(null);
    }


}
