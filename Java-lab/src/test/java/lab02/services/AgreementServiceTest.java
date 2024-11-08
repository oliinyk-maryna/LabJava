package lab02.services;

import lab02.Agreement;
import lab02.Car;
import lab02.Payment;
import lab02.PaymentMethod;
import lab02.Renter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgreementServiceTest {

    private Agreement agreement1;
    private Agreement agreement2;
    private Agreement agreement3;
    private AgreementService agreementService;

    @BeforeEach
    public void setUp() {
        // Ініціалізація тестових даних
        Renter renter1 = new Renter("John", "Doe", "ID123", "DL123");
        Renter renter2 = new Renter("Jane", "Smith", "ID456", "DL456");
        Renter renter3 = new Renter("John", "Doe", "ID789", "DL789");

        Car car1 = new Car("Toyota", "Corolla", "Sedan", "Red", 50.0, "VIN123", 2022);
        Car car2 = new Car("Ford", "Focus", "Hatchback", "Blue", 45.0, "VIN456", 2020);
        Car car3 = new Car("Honda", "Civic", "Sedan", "White", 50.0, "VIN004", 2023);

        Payment payment1 = new Payment(PaymentMethod.CREDIT_CARD, 500.0);
        Payment payment2 = new Payment(PaymentMethod.CASH, 300.0);
        Payment payment3 = new Payment(PaymentMethod.CREDIT_CARD, 700.0);

        agreement1 = new Agreement(car1, renter1, LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 20), 500.0, payment1);
        agreement2 = new Agreement(car2, renter2, LocalDate.of(2024, 2, 20), LocalDate.of(2024, 2, 24), 300.0, payment2);
        agreement3 = new Agreement(car3, renter3, LocalDate.of(2024, 1, 14), LocalDate.of(2024, 1, 20), 700.0, payment3);

        agreementService = new AgreementService(Arrays.asList(agreement1, agreement2, agreement3));
    }

    @Test
    void testFilterByRenterLastNameAndStartDate() {
        // Test filter by last name and start date
        List<Agreement> filteredAgreements = agreementService.filterByRenterLastNameAndStartDate("Smith", "2023-01-01");

        assertEquals(1, filteredAgreements.size(), "There should be 1 agreements with renter 'Smith' starting after 2023-01-01");
        assertTrue(filteredAgreements.stream().allMatch(agreement -> agreement.getRenter().getLastName().equalsIgnoreCase("Smith")),
                "All filtered agreements should have renter 'Smith'");
    }

    @Test
    void testFilterByTotalPriceRange() {
        // Test filter by total price range
        List<Agreement> filteredAgreements = agreementService.filterByTotalPriceRange(2000.0, 4000.0);

        assertEquals(1, filteredAgreements.size(), "There should be 3 agreements with total price between 2000 and 4000");
        assertTrue(filteredAgreements.stream().allMatch(agreement -> agreement.getTotalPrice() >= 2000.0 && agreement.getTotalPrice() <= 4000.0),
                "All filtered agreements should have total price between 200 and 500");
    }

    @Test
    void testSortByRentalDuration() {
        // Test sorting by rental duration (start date to end date)
        List<Agreement> sortedAgreements = agreementService.sortByRentalDuration();

        assertEquals(agreement2, sortedAgreements.get(0), "The agreement with the shortest duration should be first");
        assertEquals(agreement1, sortedAgreements.get(1), "The second shortest agreement should be second");
        assertEquals(agreement3, sortedAgreements.get(2), "The longest agreement should be last");
    }
}
