package lab05.Models;

/**
 * Represents a car with automatic transmission.
 */
public class AutomaticCar extends Car {

    /**
     * Constructor for the AutomaticCar class.
     * Initializes an automatic car with the specified attributes.
     */
    public AutomaticCar(String manufacturer, String brand, String type, String color, double costPerDay, String vinCode, int releaseYear) {
        super(manufacturer, brand, type, color, costPerDay, vinCode, releaseYear);
    }

    /**
     * Provides a string representation of the AutomaticCar object.
     *
     * @return A formatted string with the automatic car's details.
     */
    @Override
    public String toString() {
        return "AutomaticCar{" +
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
