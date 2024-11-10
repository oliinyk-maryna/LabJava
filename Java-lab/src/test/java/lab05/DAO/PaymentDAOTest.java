package lab05.DAO;

import lab05.Models.Payment;
import lab05.Models.PaymentMethod;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {

    private PaymentDAO paymentDAO;
    private Payment testPayment;

    @BeforeEach
    void setUp() throws SQLException {
        paymentDAO = new PaymentDAO();
        testPayment = new Payment(UUID.randomUUID(), PaymentMethod.CREDIT_CARD, 150.0);
        paymentDAO.create(testPayment);
    }

    @AfterEach
    void tearDown() throws SQLException {
        paymentDAO.delete(testPayment);
    }

    @Test
    void testGetAll() throws SQLException {
        List<Payment> payments = paymentDAO.getAll();
        assertNotNull(payments, "The payments list should not be null");
        assertTrue(payments.size() > 0, "The payments list should not be empty");
    }

    @Test
    void testGetById() throws SQLException {
        Optional<Payment> payment = paymentDAO.getById(testPayment.getId());
        assertTrue(payment.isPresent(), "Payment should be found by ID");
        assertEquals(testPayment.getAmount(), payment.get().getAmount(), "Amounts should match");
        assertEquals(testPayment.getPaymentMethod(), payment.get().getPaymentMethod(), "Payment methods should match");
    }

    @Test
    void testCreate() throws SQLException {
        Payment newPayment = new Payment(UUID.randomUUID(), PaymentMethod.CASH, 200.0);
        assertTrue(paymentDAO.create(newPayment), "The payment should be created successfully");

        Optional<Payment> createdPayment = paymentDAO.getById(newPayment.getId());
        assertTrue(createdPayment.isPresent(), "Created payment should be found");

        // Clean up after test
        paymentDAO.delete(newPayment);
    }

    @Test
    void testUpdate() throws SQLException {
        testPayment.setAmount(180.0);
        assertTrue(paymentDAO.update(testPayment), "Payment update should be successful");

        Optional<Payment> updatedPayment = paymentDAO.getById(testPayment.getId());
        assertTrue(updatedPayment.isPresent(), "Updated payment should be found");
        assertEquals(180.0, updatedPayment.get().getAmount(), "Amount should be updated");
    }

    @Test
    void testDelete() throws SQLException {
        Payment newPayment = new Payment(UUID.randomUUID(), PaymentMethod.BANK_TRANSFER, 300.0);
        paymentDAO.create(newPayment);
        assertTrue(paymentDAO.delete(newPayment), "Payment should be deleted successfully");

        Optional<Payment> deletedPayment = paymentDAO.getById(newPayment.getId());
        assertFalse(deletedPayment.isPresent(), "Deleted payment should not be found");
    }
}
