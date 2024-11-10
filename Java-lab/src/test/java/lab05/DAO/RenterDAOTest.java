package lab05.DAO;

import lab05.Models.Renter;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RenterDAOTest {

    private RenterDAO renterDAO;
    private Renter testRenter;

    @BeforeEach
    void setUp() throws SQLException {
        renterDAO = new RenterDAO();
        testRenter = new Renter(UUID.randomUUID(), "David", "Wilson", "22222", "D2222222");
        renterDAO.create(testRenter);
    }

    @AfterEach
    void tearDown() throws SQLException {
        renterDAO.delete(testRenter);
    }

    @Test
    void testGetAll() throws SQLException {
        List<Renter> renters = renterDAO.getAll();
        assertNotNull(renters, "The renters list should not be null");
        assertTrue(renters.size() > 0, "The renters list should not be empty");
    }

    @Test
    void testGetById() throws SQLException {
        Optional<Renter> renter = renterDAO.getById(testRenter.getId());
        assertTrue(renter.isPresent(), "Renter should be found by ID");
        assertEquals(testRenter.getIdentityDocument(), renter.get().getIdentityDocument(), "Identity documents should match");
    }

    @Test
    void testCreate() throws SQLException {
        Renter newRenter = new Renter(UUID.randomUUID(), "Sarah", "Miller", "33333", "D3333333");
        assertTrue(renterDAO.create(newRenter), "The renter should be created successfully");

        // Clean up after test
        renterDAO.delete(newRenter);
    }

    @Test
    void testUpdate() throws SQLException {
        testRenter.setLastName("Williams");
        testRenter.setIdentityDocument("33333");
        assertTrue(renterDAO.update(testRenter), "Renter update should be successful");

        Optional<Renter> updatedRenter = renterDAO.getById(testRenter.getId());
        assertTrue(updatedRenter.isPresent(), "Updated renter should be found");
        assertEquals("Williams", updatedRenter.get().getLastName(), "Last name should be updated");
        assertEquals("33333", updatedRenter.get().getIdentityDocument(), "Identity document should be updated");
    }

    @Test
    void testDelete() throws SQLException {
        Renter newRenter = new Renter(UUID.randomUUID(), "John", "Doe", "44444", "D4444444");
        renterDAO.create(newRenter);
        assertTrue(renterDAO.delete(newRenter), "Renter should be deleted successfully");

        Optional<Renter> deletedRenter = renterDAO.getById(newRenter.getId());
        assertFalse(deletedRenter.isPresent(), "Deleted renter should not be found");
    }
}
