package lab01;

import java.util.Objects;

/**
 * Represents a payment in the car rental system.
 */
public class Payment {
    private PaymentMethod paymentMethod;  // Payment method (e.g., credit card, cash)
    private double amount;                // Payment amount

    /**
     * Constructs a Payment with the specified payment method and amount.
     *
     * @param paymentMethod the method of payment
     * @param amount        the amount of the payment
     */
    public Payment(PaymentMethod paymentMethod, double amount) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 &&
                paymentMethod == payment.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethod, amount);
    }
}