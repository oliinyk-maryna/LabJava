package lab03;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a Car in the rental system.
 */
public class Car implements Comparable<Car> {
    private final UUID id;                 // Unique ID of the car
    private String manufacturer;           // Manufacturer of the car
    private String brand;                  // Brand of the car
    private String type;                   // Type of the car
    private String color;                  // Color of the car
    private double costPerDay;             // Daily rental cost
    private String vinCode;                // VIN code of the car
    private int releaseYear;               // Release year of the car
    private final LocalDateTime createdAt; // Timestamp when the car was added to the system

    /**
     * Default constructor for the Car class.
     */
    public Car() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Constructor for the Car class with parameters.
     * @param manufacturer The manufacturer of the car.
     * @param brand The brand of the car.
     * @param type The type of the car.
     * @param color The color of the car.
     * @param costPerDay The daily rental cost of the car.
     * @param vinCode The VIN code of the car.
     * @param releaseYear The release year of the car.
     */
    public Car(String manufacturer, String brand, String type, String color, double costPerDay, String vinCode, int releaseYear) {
        this();
        this.manufacturer = manufacturer;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.costPerDay = costPerDay;
        this.vinCode = vinCode;
        this.releaseYear = releaseYear;
    }

    /**
     * Gets the unique ID of the car.
     * @return UUID representing the unique ID of the car.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the creation date and time of the car object.
     * @return LocalDateTime when the car was added to the system.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    // Getters and setters for each attribute
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Generates a string representation of the Car object.
     * @return A string representing the Car object.
     */
    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", costPerDay=" + costPerDay +
                ", vinCode='" + vinCode + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    /**
     * Compares this Car object with another object for equality.
     * @param o The object to compare.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.costPerDay, costPerDay) == 0 &&
                releaseYear == car.releaseYear &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(type, car.type) &&
                Objects.equals(color, car.color) &&
                Objects.equals(vinCode, car.vinCode);
    }

    /**
     * Generates the hash code for the Car object.
     * @return The hash code of the Car.
     */
    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, brand, type, color, costPerDay, vinCode, releaseYear);
    }

    @Override
    public int compareTo(Car other) {
        // Compare by release year first
        int yearComparison = Integer.compare(this.releaseYear, other.releaseYear);
        if (yearComparison != 0) {
            return yearComparison;
        }
        // If release years are the same, compare by brand alphabetically
        return this.brand.compareTo(other.brand);
    }
}
