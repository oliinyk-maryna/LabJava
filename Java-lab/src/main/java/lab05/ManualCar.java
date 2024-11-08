package lab05;

/**
 * Represents a car with manual transmission in the rental system.
 */
public class ManualCar extends Car {

    /**
     * Constructor for the ManualCar class.
     * Initializes a manual car with the specified attributes.
     *
     * @param manufacturer The manufacturer of the car.
     * @param brand The brand of the car.
     * @param type The type of the car.
     * @param color The color of the car.
     * @param costPerDay The daily rental cost of the car.
     * @param vinCode The VIN code of the car.
     * @param releaseYear The release year of the car.
     */
    public ManualCar(String manufacturer, String brand, String type, String color, double costPerDay, String vinCode, int releaseYear) {
        super(manufacturer, brand, type, color, costPerDay, vinCode, releaseYear);
    }

    /**
     * Provides a string representation of the ManualCar object.
     *
     * @return A formatted string with the manual car's details.
     */
    @Override
    public String toString() {
        return "ManualCar{" +
                "manufacturer='" + getManufacturer() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", type='" + getType() + '\'' +
                ", color='" + getColor() + '\'' +
                ", costPerDay=" + getCostPerDay() +
                ", vinCode='" + getVinCode() + '\'' +
                ", releaseYear=" + getReleaseYear() +
                '}';
    }

}
