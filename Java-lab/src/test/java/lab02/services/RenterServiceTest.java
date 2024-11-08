package lab02.services;

import lab02.Renter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class RenterServiceTest {

    private List<Renter> renters;
    private RenterService renterService;

    @BeforeEach
    void setUp() {
        // Sample Renter instances
        Renter renter1 = new Renter("Alice", "Smith", "ID123", "DL123");
        Renter renter2 = new Renter("Bob", "Johnson", "ID456", "DL456");
        Renter renter3 = new Renter("Alice", "Smith", "ID789", "DL789");
        Renter renter4 = new Renter("John", "Doe", "ID012", "DL123");

        renters = Arrays.asList(renter1, renter2, renter3, renter4);
        renterService = new RenterService(renters);
    }

    @Test
    void testFilterByIdentityDocumentAndLicense() {
        // Test filter by identity document and driver's license
        List<Renter> filteredRenters = renterService.filterByIdentityDocumentAndLicense("ID123", "DL123");

        assertEquals(1, filteredRenters.size(), "There should be 1 renter with ID123 and DL123");
        assertEquals("Alice", filteredRenters.get(0).getFirstName(), "The filtered renter should be Alice");
        assertEquals("Smith", filteredRenters.get(0).getLastName(), "The filtered renter should have last name Smith");
    }

    @Test
    void testFilterByName() {
        // Test filter by last name and first name
        List<Renter> filteredRenters = renterService.filterByName("Smith", "Alice");

        assertEquals(2, filteredRenters.size(), "There should be 2 renters with the name Alice Smith");
        assertTrue(filteredRenters.stream().allMatch(renter -> renter.getFirstName().equals("Alice")),
                "All filtered renters should have the first name Alice");
        assertTrue(filteredRenters.stream().allMatch(renter -> renter.getLastName().equals("Smith")),
                "All filtered renters should have the last name Smith");
    }
}
