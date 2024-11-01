package lab02.services;

import lab02.Agreement;
import lab02.Car;
import lab02.Renter;
import lab02.PaymentMethod;
import lab02.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private PaymentService paymentService;
    private Agreement agreement;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();

        // Create a Car instance (use your Car constructor)
        Car car = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);

        // Create a Renter instance (use your Renter constructor)
        Renter renter = new Renter("John Doe", "123456789", "AB123456", "555-1234");

        // Define start and end dates
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(5); // For example, a 5-day rental

        // Define total price
        double totalPrice = 250.0; // Assume some total price

        // Create a Payment instance with the necessary parameters
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD; // Adjust based on your enum values
        Payment payment = new Payment(paymentMethod, totalPrice);

        // Create an Agreement instance with the correct parameters
        agreement = new Agreement(car, renter, startDate, endDate, totalPrice, payment);
    }

    @Test
    void testProcessPayment() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD; // Ensure you have this enum defined
        paymentService.processPayment(agreement, paymentMethod);

        assertEquals(1, paymentService.listPaymentsByAgreement(agreement.getId()).size(),
                "Expected one payment record to be created.");
    }

    @Test
    void testRefundPayment() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        paymentService.processPayment(agreement, paymentMethod);

        assertTrue(paymentService.refundPayment(agreement.getId()),
                "Expected the refund to be successful.");

        assertEquals(0, paymentService.listPaymentsByAgreement(agreement.getId()).size(),
                "Expected no payment records after refund.");
    }

    @Test
    void testListPaymentsByAgreement() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        paymentService.processPayment(agreement, paymentMethod);

        assertEquals(1, paymentService.listPaymentsByAgreement(agreement.getId()).size(),
                "Expected one payment record to be listed.");
    }
}
