package lab03;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Class representing a rental agreement in the car rental system.
 * Stores information about the rented car, the customer (renter), the rental period,
 * daily price, and payment details.
 */
public class Agreement implements Comparable<Agreement> {
    private final UUID id;
    private final Car car;
    private final Renter renter;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Payment payment;

    @JsonCreator
    public Agreement(
            @JsonProperty("car") Car car,
            @JsonProperty("renter") Renter renter,
            @JsonProperty("startDate") LocalDate startDate,
            @JsonProperty("endDate") LocalDate endDate,
            @JsonProperty("payment") Payment payment) {
        this.id = UUID.randomUUID();
        this.car = car;
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payment = payment;
    }

    public UUID getId() {
        return id;
    }

    @JsonProperty("getCar")
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
     * Gets the payment information for the rental.
     * @return The payment details.
     */
    public Payment getPayment() {
        return payment;
    }


    @JsonProperty("totalPrice")
    public double getTotalPrice() {
        long rentalDays = endDate.toEpochDay() - startDate.toEpochDay();
        return car.getCostPerDay() * rentalDays;
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

        return Objects.equals(id, that.id) &&
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
        return Objects.hash(id, car, renter, startDate, endDate, payment); // Include UUID in hashCode
    }

    @Override
    public int compareTo(Agreement other) {
        int result = this.startDate.compareTo(other.startDate);
        if (result == 0) {
            result = this.endDate.compareTo(other.endDate);
        }
        if (result == 0) {
            result = Double.compare(this.getTotalPrice(), other.getTotalPrice());
        }
        if (result == 0) {
            result = this.car.getBrand().compareTo(other.car.getBrand());
        }
        return result;
    }

}
