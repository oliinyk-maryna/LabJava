package lab01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Agreement class.
 */
class AgreementTest {

    private Car car;
    private Renter renter;
    private Payment payment;
    private Agreement agreement1;
    private Agreement agreement2;

    @BeforeEach
    void setUp() {
        car = new Car("Toyota", "Corolla", "Sedan", "White", 40.0, "1HGCM82633A123456", 2020);
        renter = new Renter("John", "Doe", "Passport123", "DL123456");
        payment = new Payment(PaymentMethod.CREDIT_CARD, 240.0);

        LocalDate startDate = LocalDate.of(2024, 10, 1);
        LocalDate endDate = LocalDate.of(2024, 10, 7);

        agreement1 = new Agreement(car, renter, startDate, endDate, 40.0, payment);
        agreement2 = new Agreement(car, renter, startDate, endDate, 40.0, payment);
    }

    @Test
    void testGetTotalPrice() {
        // Duration is 6 days, daily price is 40.0, so total should be 240.0
        assertEquals(240.0, agreement1.getTotalPrice());
    }

    @Test
    void testToString() {
        String expected = "Agreement{" +
                "car=Car{manufacturer='Toyota', brand='Corolla', type='Sedan', color='White', costPerDay=40.0, vinCode='1HGCM82633A123456', releaseYear=2020}" +
                ", customer=Renter{firstName='John', lastName='Doe', identityDocument='Passport123', driversLicense='DL123456'}" +
                ", startDate=2024-10-01" +
                ", endDate=2024-10-07" +
                ", dailyPrice=40.0" +
                ", totalPrice=240.0" +
                ", payment=Payment{paymentMethod=Credit Card, amount=240.0}" +
                '}';

        assertEquals(expected, agreement1.toString());
    }


    @Test
    void testEquals() {
        assertEquals(agreement1, agreement2);
    }

    @Test
    void testHashCode() {
        assertEquals(agreement1.hashCode(), agreement2.hashCode());
    }

    @Test
    void testNotEquals() {
        Agreement differentAgreement = new Agreement(car, renter, LocalDate.of(2024, 10, 2), LocalDate.of(2024, 10, 8), 50.0, payment);
        assertNotEquals(agreement1, differentAgreement);
    }
}
