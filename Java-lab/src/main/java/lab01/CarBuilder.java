package lab01;

/**
 * Builder class for creating and configuring Car objects.
 */
public class CarBuilder {
    private String manufacturer;
    private String brand;
    private String type;
    private String color;
    private double costPerDay;
    private String vinCode;
    private int releaseYear;

    /**
     * Sets the manufacturer of the car.
     * @param manufacturer The manufacturer to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    /**
     * Sets the brand of the car.
     * @param brand The brand to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    /**
     * Sets the type of the car.
     * @param type The type to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the color of the car.
     * @param color The color to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the rental cost per day of the car.
     * @param costPerDay The cost per day to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
        return this;
    }

    /**
     * Sets the VIN code of the car.
     * @param vinCode The VIN code to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setVinCode(String vinCode) {
        this.vinCode = vinCode;
        return this;
    }

    /**
     * Sets the release year of the car.
     * @param releaseYear The release year to set for the car.
     * @return The current instance of CarBuilder for method chaining.
     */
    public CarBuilder setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    /**
     * Builds and configures a new Car instance with the set properties.
     * @return The configured Car object.
     */
    public Car build() {
        Car car = new Car();
        car.setManufacturer(this.manufacturer);
        car.setBrand(this.brand);
        car.setType(this.type);
        car.setColor(this.color);
        car.setCostPerDay(this.costPerDay);
        car.setVinCode(this.vinCode);
        car.setReleaseYear(this.releaseYear);
        return car;
    }
}
