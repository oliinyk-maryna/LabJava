package lab05.DAO;

import lab05.Models.Payment;
import lab05.Models.PaymentMethod;
import lab05.Interfaces.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PaymentDAO implements IDAO<Payment> {

    private static final String INSERT_PAYMENT = """
        INSERT INTO Payment (id, payment_method, amount) VALUES (?, ?, ?)
    """;

    private static final String SELECT_ALL_PAYMENTS = """
        SELECT * FROM Payment
    """;

    private static final String SELECT_PAYMENT_BY_ID = """
        SELECT * FROM Payment WHERE id = ?
    """;

    private static final String UPDATE_PAYMENT = """
        UPDATE Payment SET payment_method = ?, amount = ? WHERE id = ?
    """;

    private static final String DELETE_PAYMENT = """
        DELETE FROM Payment WHERE id = ?
    """;

    @Override
    public List<Payment> getAll() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL_PAYMENTS)) {

            while (rs.next()) {
                payments.add(mapToPayment(rs));
            }
        }
        return payments;
    }

    @Override
    public Optional<Payment> getById(UUID id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PAYMENT_BY_ID)) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToPayment(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean create(Payment payment) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT)) {

            ps.setObject(1, payment.getId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setDouble(3, payment.getAmount());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Payment payment) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT)) {

            ps.setString(1, payment.getPaymentMethod());
            ps.setDouble(2, payment.getAmount());
            ps.setObject(3, payment.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Payment payment) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_PAYMENT)) {

            ps.setObject(1, payment.getId());
            return ps.executeUpdate() > 0;
        }
    }

    private Payment mapToPayment(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        PaymentMethod paymentMethod = PaymentMethod.valueOf(rs.getString("payment_method")); // Convert String to PaymentMethod
        double amount = rs.getDouble("amount");

        return new Payment(id, paymentMethod, amount);
    }

}
