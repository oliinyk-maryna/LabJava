package lab05.Models;

/**
 * Represents a car with semi-automatic transmission in the rental system.
 */
public class SemiAutomaticCar extends Car {

    /**
     * Constructor for the SemiAutomaticCar class.
     * Initializes a semi-automatic car with the specified attributes.
     *
     * @param manufacturer The manufacturer of the car.
     * @param brand The brand of the car.
     * @param type The type of the car.
     * @param color The color of the car.
     * @param costPerDay The daily rental cost of the car.
     * @param vinCode The VIN code of the car.
     * @param releaseYear The release year of the car.
     */
    public SemiAutomaticCar(String manufacturer, String brand, String type, String color, double costPerDay, String vinCode, int releaseYear) {
        super(manufacturer, brand, type, color, costPerDay, vinCode, releaseYear);
    }

    /**
     * Provides a string representation of the SemiAutomaticCar object.
     *
     * @return A formatted string with the semi-automatic car's details.
     */
    @Override
    public String toString() {
        return "SemiAutomaticCar{" +
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
