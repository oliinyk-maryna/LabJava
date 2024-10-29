package lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Payment class.
 */
public class PaymentTest {

    @Test
    public void testConstructorAndGetters() {
        Payment payment = new Payment(PaymentMethod.CREDIT_CARD, 150.0);

        assertEquals(PaymentMethod.CREDIT_CARD, payment.getPaymentMethod());
        assertEquals(150.0, payment.getAmount(), 0.001);
    }

    @Test
    public void testEquals_SameValues_ShouldReturnTrue() {
        Payment payment1 = new Payment(PaymentMethod.CASH, 50.0);
        Payment payment2 = new Payment(PaymentMethod.CASH, 50.0);

        assertEquals(payment1, payment2);
    }

    @Test
    public void testEquals_DifferentValues_ShouldReturnFalse() {
        Payment payment1 = new Payment(PaymentMethod.PAYPAL, 75.0);
        Payment payment2 = new Payment(PaymentMethod.CREDIT_CARD, 75.0);
        Payment payment3 = new Payment(PaymentMethod.PAYPAL, 100.0);

        assertNotEquals(payment1, payment2);
        assertNotEquals(payment1, payment3);
    }

    @Test
    public void testHashCode_SameValues_ShouldReturnSameHashCode() {
        Payment payment1 = new Payment(PaymentMethod.BANK_TRANSFER, 200.0);
        Payment payment2 = new Payment(PaymentMethod.BANK_TRANSFER, 200.0);

        assertEquals(payment1.hashCode(), payment2.hashCode());
    }

    @Test
    public void testHashCode_DifferentValues_ShouldReturnDifferentHashCode() {
        Payment payment1 = new Payment(PaymentMethod.DEBIT_CARD, 120.0);
        Payment payment2 = new Payment(PaymentMethod.DEBIT_CARD, 100.0);

        assertNotEquals(payment1.hashCode(), payment2.hashCode());
    }

    @Test
    public void testToString() {
        Payment payment = new Payment(PaymentMethod.CREDIT_CARD, 150.0);
        String expected = "Payment{paymentMethod=CREDIT_CARD, amount=150.0}";

        assertEquals(expected, payment.toString());
    }
}
