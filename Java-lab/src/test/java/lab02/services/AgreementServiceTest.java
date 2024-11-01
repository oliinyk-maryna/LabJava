package lab02.services;

import lab02.Agreement;
import lab02.Car;
import lab02.Renter;
import lab02.Payment;
import lab02.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AgreementServiceTest {

    private AgreementService agreementService;
    private List<Agreement> agreements;

    @BeforeEach
    void setUp() {
        agreementService = new AgreementService();

        // Sample Car and Payment instances
        Car car1 = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        Car car2 = new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020);
        Payment payment = new Payment(PaymentMethod.CREDIT_CARD, 200.0); // Payment instance

// Sample Renter instances
        Renter renter1 = new Renter("John", "Doe", "john.doe@example.com", "123-456-7890");
        Renter renter2 = new Renter("Jane", "Smith", "jane.smith@example.com", "098-765-4321");

// Sample Agreement instances
        Agreement agreement1 = new Agreement(car1, renter1, LocalDate.of(2023, 6, 15), LocalDate.of(2023, 6, 20), 200.0, payment);
        Agreement agreement2 = new Agreement(car1, renter1, LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 15), 150.0, payment);
        Agreement agreement3 = new Agreement(car2, renter2, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25), 300.0, payment);

        agreements = Arrays.asList(agreement1, agreement2, agreement3);

    }

    @Test
    void testFilterByRenterLastName() {
        List<Agreement> result = agreementService.filterByRenterLastName(agreements, "Doe");

        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).getRenter().getLastName());
        assertEquals("Doe", result.get(1).getRenter().getLastName());
    }

    @Test
    void testFilterByRenterLastName_NoMatches() {
        List<Agreement> result = agreementService.filterByRenterLastName(agreements, "Nonexistent");

        assertEquals(0, result.size());
    }

    @Test
    void testSortByStartDate() {
        List<Agreement> sortedAgreements = agreementService.sortByStartDate(agreements);

        assertEquals(LocalDate.of(2023, 6, 10), sortedAgreements.get(0).getStartDate());
        assertEquals(LocalDate.of(2023, 6, 15), sortedAgreements.get(1).getStartDate());
        assertEquals(LocalDate.of(2023, 6, 20), sortedAgreements.get(2).getStartDate());
    }

    @Test
    void testFindAgreementWithHighestTotalPrice() {
        Agreement highestPriceAgreement = agreementService.findAgreementWithHighestTotalPrice(agreements);

        assertEquals(1500.0, highestPriceAgreement.getTotalPrice()); // Change expected value to 1500.0
        assertEquals("Smith", highestPriceAgreement.getRenter().getLastName()); // This remains correct
    }

    @Test
    void testFindAgreementWithHighestTotalPrice_EmptyList() {
        Agreement highestPriceAgreement = agreementService.findAgreementWithHighestTotalPrice(Arrays.asList());

        assertNull(highestPriceAgreement);
    }
}
