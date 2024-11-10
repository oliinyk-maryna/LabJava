package lab05.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a payment in the car rental system.
 */
public class Payment {
    public static Object PaymentMethod;
    private UUID id;                      // Unique identifier for the payment
    private PaymentMethod paymentMethod;  // Payment method (e.g., credit card, cash)
    private double amount;                // Payment amount

    @JsonCreator
    public Payment(@JsonProperty("id") UUID id,
                   @JsonProperty("paymentMethod") PaymentMethod paymentMethod,
                   @JsonProperty("amount") double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    // Getter for id
    public UUID getId() {
        return id;
    }

    public String getPaymentMethod() {
        return paymentMethod.name();
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 &&
                Objects.equals(id, payment.id) &&
                paymentMethod == payment.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, amount);
    }
}
