package lab02.comparators;

import lab02.Agreement;
import lab02.Car;
import lab02.Payment;
import lab02.Renter;
import lab02.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgreementComparatorTest {

    private Agreement agreement1;
    private Agreement agreement2;
    private Agreement agreement3;

    @BeforeEach
    void setUp() {
        // Sample data setup
        Renter renter1 = new Renter("John", "Doe", "ID001", "DL001");
        Renter renter2 = new Renter("Jane", "Smith", "ID002", "DL002");
        Renter renter3 = new Renter("Alice", "Brown", "ID003", "DL003");

        Car car = new Car("Toyota", "Corolla", "Sedan", "Blue", 50, "VIN001", 2020);
        Payment payment = new Payment(PaymentMethod.CREDIT_CARD, 500);

        agreement1 = new Agreement(car, renter1, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 10), 30, payment);
        agreement2 = new Agreement(car, renter2, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 10), 25, payment);
        agreement3 = new Agreement(car, renter3, LocalDate.of(2023, 1, 5), LocalDate.of(2023, 1, 15), 30, payment);
    }

    @Test
    void testByStartDate() {
        List<Agreement> agreements = Arrays.asList(agreement2, agreement1, agreement3);
        agreements.sort(AgreementComparator.byStartDate());

        assertEquals(agreement1, agreements.get(0));
        assertEquals(agreement3, agreements.get(1));
        assertEquals(agreement2, agreements.get(2));
    }

    @Test
    void testByTotalPrice() {
        List<Agreement> agreements = Arrays.asList(agreement1, agreement2, agreement3);
        agreements.sort(AgreementComparator.byTotalPrice());

        assertEquals(agreement2, agreements.get(0)); // Lowest price first
        assertEquals(agreement1, agreements.get(1)); // agreement1 and agreement3 have same total price, sorted by start date
        assertEquals(agreement3, agreements.get(2));
    }

    @Test
    void testByCustomerName() {
        List<Agreement> agreements = Arrays.asList(agreement2, agreement3, agreement1);
        agreements.sort(AgreementComparator.byCustomerName());

        assertEquals(agreement3, agreements.get(0)); // Alice Brown
        assertEquals(agreement1, agreements.get(1)); // John Doe
        assertEquals(agreement2, agreements.get(2)); // Jane Smith
    }
}
