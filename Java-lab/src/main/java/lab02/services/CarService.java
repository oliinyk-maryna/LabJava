package lab02.services;

import lab02.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.time.LocalDateTime;

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
