package lab05.DAO;

import lab05.Models.Agreement;
import lab05.Models.Car;
import lab05.Models.Payment;
import lab05.Models.PaymentMethod;
import lab05.Models.Renter;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDAOTest {

    private AgreementDAO agreementDAO;
    private CarDAO carDAO;
    private RenterDAO renterDAO;
    private PaymentDAO paymentDAO;

    private Agreement testAgreement;
    private Car testCar;
    private Renter testRenter;
    private Payment testPayment;

    @BeforeEach
    void setUp() throws SQLException {
        carDAO = new CarDAO();
        renterDAO = new RenterDAO();
        paymentDAO = new PaymentDAO();
        agreementDAO = new AgreementDAO();
        String vinCode = "VIN" + UUID.randomUUID().toString().substring(0, 10);
        testCar = new Car("Toyota", "Corolla", "Sedan", "Blue", 100.0, vinCode, 2022);
        carDAO.create(testCar);

        testRenter = new Renter(UUID.randomUUID(), "John", "Doe", "ID12345", "DL6789");
        renterDAO.create(testRenter);

        testPayment = new Payment(UUID.randomUUID(), PaymentMethod.CREDIT_CARD, 200.0);
        paymentDAO.create(testPayment);

        testAgreement = new Agreement(testCar, testRenter, LocalDate.now(), LocalDate.now().plusDays(5), testPayment);
        agreementDAO.create(testAgreement);
    }

    @AfterEach
    void tearDown() throws SQLException {
        agreementDAO.delete(testAgreement);
        carDAO.delete(testCar);
        renterDAO.delete(testRenter);
        paymentDAO.delete(testPayment);
    }

    @Test
    void testGetAll() throws SQLException {
        List<Agreement> agreements = agreementDAO.getAll();
        assertNotNull(agreements);
        assertTrue(agreements.size() > 0, "The agreement list should not be empty");
    }

    @Test
    void testGetById() throws SQLException {
        Optional<Agreement> agreement = agreementDAO.getById(testAgreement.getId());
        assertTrue(agreement.isPresent(), "Agreement should be found");

        assertEquals(testRenter.getId(), agreement.get().getRenter().getId(), "The renter IDs should match");
        assertEquals(testAgreement.getStartDate(), agreement.get().getStartDate(), "The start dates should match");
    }


    @Test
    void testCreate() throws SQLException {
        Agreement newAgreement = new Agreement(testCar, testRenter, LocalDate.now(), LocalDate.now().plusDays(7), testPayment);
        assertTrue(agreementDAO.create(newAgreement), "The agreement should be created successfully");
        agreementDAO.delete(newAgreement);
    }

    @Test
    void testUpdate() throws SQLException {
        testAgreement.setStartDate(LocalDate.now().plusDays(1));
        assertTrue(agreementDAO.update(testAgreement), "The agreement update should be successful");

        Optional<Agreement> updatedAgreement = agreementDAO.getById(testAgreement.getId());
        assertTrue(updatedAgreement.isPresent(), "Updated agreement should be found");
        assertEquals(LocalDate.now().plusDays(1), updatedAgreement.get().getStartDate(), "The start date should be updated");
    }

    @Test
    void testDelete() throws SQLException {
        Agreement newAgreement = new Agreement(testCar, testRenter, LocalDate.now(), LocalDate.now().plusDays(7), testPayment);
        agreementDAO.create(newAgreement);
        assertTrue(agreementDAO.delete(newAgreement), "The agreement should be deleted successfully");

        Optional<Agreement> deletedAgreement = agreementDAO.getById(newAgreement.getId());
        assertFalse(deletedAgreement.isPresent(), "Deleted agreement should not be found");
    }
}
