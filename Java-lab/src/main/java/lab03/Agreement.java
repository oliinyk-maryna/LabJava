package lab03;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a rental agreement in the car rental system.
 * Stores information about the rented car, the customer (renter), the rental period,
 * daily price, and payment details.
 */
public class Agreement implements Comparable<Agreement> {
    private final UUID id;                  // Unique ID of the agreement
    private final Car car;                   // The car being rented
    private final Renter renter;             // The customer renting the car
    private final LocalDate startDate;       // The start date of the rental
    private final LocalDate endDate;         // The end date of the rental
    private final double dailyPrice;         // The daily price of the rental
    private final Payment payment;            // Payment information for the rental

    public Agreement(Car car, Renter renter, LocalDate startDate, LocalDate endDate, double dailyPrice, Payment payment) {
        this.id = UUID.randomUUID();          // Generate a unique ID for the agreement
        this.car = car;
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
        this.payment = payment;
    }

    public UUID getId() {
        return id;                             // Getter for the UUID
    }

    /**
     * Gets the car associated with the agreement.
     * @return The car being rented.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Gets the customer renting the car.
     * @return The renter of the car.
     */
    public Renter getRenter() {
        return renter;
    }

    /**
     * Gets the start date of the rental period.
     * @return The start date of the rental.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the rental period.
     * @return The end date of the rental.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the daily rental price.
     * @return The daily price of the rental.
     */
    public double getDailyPrice() {
        return dailyPrice;
    }

    /**
     * Gets the payment information for the rental.
     * @return The payment details.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Calculates the total rental cost based on the rental period and daily price.
     * @return The total price for the rental.
     */
    public double getTotalPrice() {
        long rentalDays = endDate.toEpochDay() - startDate.toEpochDay();
        return dailyPrice * rentalDays;
    }

    /**
     * Provides a string representation of the Agreement object.
     * @return A formatted string with the agreement details.
     */
    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +                               // Include UUID in toString()
                ", car=" + car.toString() +
                ", customer=" + renter.toString() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dailyPrice=" + dailyPrice +
                ", totalPrice=" + getTotalPrice() +
                ", payment=" + payment.toString() +
                '}';
    }

    /**
     * Compares this agreement to another object for equality.
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement that = (Agreement) o;
        return Double.compare(that.dailyPrice, dailyPrice) == 0 &&
                Objects.equals(id, that.id) &&               // Compare UUIDs for equality
                Objects.equals(car, that.car) &&
                Objects.equals(renter, that.renter) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(payment, that.payment);
    }

    /**
     * Generates a hash code for the Agreement object.
     * @return A hash code based on the agreement's attributes.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, car, renter, startDate, endDate, dailyPrice, payment); // Include UUID in hashCode
    }

    @Override
    public int compareTo(Agreement other) {
        return this.startDate.compareTo(other.startDate);
    }
}
