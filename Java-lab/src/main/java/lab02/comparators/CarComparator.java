package lab02.comparators;
import lab02.Car;

import java.util.Comparator;

/**
 * The {@code CarComparator} class provides several static methods for comparing
 * {@link Car} objects based on different fields such as release year, cost per day,
 * brand, and manufacturer.
 */
public class CarComparator {
    /**
     * Returns a comparator that compares {@link Car} objects by their release year.
     *
     * @return a comparator that compares {@link Car} objects by release year
     */
    public static Comparator<Car> byReleaseYear() {
        return Comparator.comparingInt(Car::getReleaseYear);
    }

    /**
     * Returns a comparator that compares {@link Car} objects by their cost per day.
     * If two cars have the same cost, they are compared by release year.
     *
     * @return a comparator that compares {@link Car} objects by cost per day
     *         and then by release year if the costs are the same
     */
    public static Comparator<Car> byCostPerDay() {
        return Comparator
                .comparingDouble(Car::getCostPerDay)
                .thenComparingInt(Car::getReleaseYear);
    }

    /**
     * Returns a comparator that compares {@link Car} objects by their brand.
     *
     * @return a comparator that compares {@link Car} objects by brand
     */
    public static Comparator<Car> byBrand() {
        return Comparator.comparing(Car::getBrand);
    }

    /**
     * Returns a comparator that compares {@link Car} objects by their manufacturer.
     * If two cars have the same manufacturer, they are compared by brand.
     *
     * @return a comparator that compares {@link Car} objects by manufacturer
     *         and then by brand if the manufacturers are the same
     */
    public static Comparator<Car> byManufacturer() {
        return Comparator
                .comparing(Car::getManufacturer)
                .thenComparing(Car::getBrand);
    }
}

