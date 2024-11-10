package lab05.DAO;

import lab05.Models.Agreement;
import lab05.Models.Car;
import lab05.Models.Renter;
import lab05.Models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AgreementDAO {

    private static final String CREATE_TABLE_AGREEMENT = """
        CREATE TABLE IF NOT EXISTS Agreement (
            id UUID PRIMARY KEY,
            car_id UUID REFERENCES Car(id),
            renter_id UUID REFERENCES Renter(id),
            start_date DATE,
            end_date DATE,
            payment_id UUID REFERENCES Payment(id)
        );
    """;

    private static final String CREATE_AGREEMENT = """
        INSERT INTO Agreement (id, car_id, renter_id, start_date, end_date, payment_id)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

    private static final String GET_ALL_AGREEMENTS = """
        SELECT * FROM Agreement
    """;

    private static final String GET_AGREEMENT_BY_ID = """
        SELECT * FROM Agreement WHERE id = ?
    """;

    private static final String UPDATE_AGREEMENT = """
        UPDATE Agreement
        SET car_id = ?, renter_id = ?, start_date = ?, end_date = ?, payment_id = ?
        WHERE id = ?
    """;

    private static final String DELETE_AGREEMENT = """
        DELETE FROM Agreement WHERE id = ?
    """;
    
    public boolean create(Agreement agreement) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_AGREEMENT)) {

            statement.setObject(1, agreement.getId());
            statement.setObject(2, agreement.getCar().getId());
            statement.setObject(3, agreement.getRenter().getId());
            statement.setDate(4, Date.valueOf(agreement.getStartDate()));
            statement.setDate(5, Date.valueOf(agreement.getEndDate()));
            statement.setObject(6, agreement.getPayment().getId());

            return statement.executeUpdate() > 0;
        }
    }

    public List<Agreement> getAll() throws SQLException {
        List<Agreement> agreements = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_AGREEMENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Agreement agreement = mapResultSetToAgreement(resultSet);
                agreements.add(agreement);
            }
        }
        return agreements;
    }

    public Optional<Agreement> getById(UUID id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_AGREEMENT_BY_ID)) {

            statement.setObject(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToAgreement(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    public boolean update(Agreement agreement) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_AGREEMENT)) {

            statement.setObject(1, agreement.getCar().getId());
            statement.setObject(2, agreement.getRenter().getId());
            statement.setDate(3, Date.valueOf(agreement.getStartDate()));
            statement.setDate(4, Date.valueOf(agreement.getEndDate()));
            statement.setObject(5, agreement.getPayment().getId());
            statement.setObject(6, agreement.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(Agreement agreement) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_AGREEMENT)) {

            statement.setObject(1, agreement.getId());
            return statement.executeUpdate() > 0;
        }
    }

    private Agreement mapResultSetToAgreement(ResultSet resultSet) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        UUID carId = (UUID) resultSet.getObject("car_id");
        UUID renterId = (UUID) resultSet.getObject("renter_id");
        Date startDate = resultSet.getDate("start_date");
        Date endDate = resultSet.getDate("end_date");
        UUID paymentId = (UUID) resultSet.getObject("payment_id");

        Car car = new CarDAO().getById(carId).orElse(null);
        Renter renter = new RenterDAO().getById(renterId).orElse(null);
        Payment payment = new PaymentDAO().getById(paymentId).orElse(null);

        return new Agreement(car, renter, startDate.toLocalDate(), endDate.toLocalDate(), payment);
    }

}