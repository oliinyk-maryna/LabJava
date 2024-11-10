package lab05.DAO;

import lab05.Interfaces.IDAO;
import lab05.Models.Renter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RenterDAO implements IDAO<Renter> {

    private static final String INSERT_RENTER = """
            INSERT INTO Renter
            (id, first_name, last_name, identity_document, drivers_license)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String SELECT_ALL_RENTERS = """
            SELECT *
            FROM Renter
            """;

    private static final String SELECT_RENTER_BY_ID = """
            SELECT *
            FROM Renter
            WHERE id = ?
            """;

    private static final String UPDATE_RENTER = """
            UPDATE Renter
            SET first_name = ?, last_name = ?, identity_document = ?, drivers_license = ?
            WHERE id = ?
            """;

    private static final String DELETE_RENTER = """
            DELETE FROM Renter
            WHERE id = ?
            """;

    @Override
    public List<Renter> getAll() throws SQLException {
        List<Renter> renters = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL_RENTERS)) {

            while (rs.next()) {
                renters.add(mapToRenter(rs));
            }
        }
        return renters;
    }

    @Override
    public Optional<Renter> getById(UUID id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_RENTER_BY_ID)) {

            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToRenter(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean create(Renter renter) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_RENTER)) {

            ps.setObject(1, renter.getId());
            ps.setString(2, renter.getFirstName());
            ps.setString(3, renter.getLastName());
            ps.setString(4, renter.getIdentityDocument());
            ps.setString(5, renter.getDriversLicense());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Renter renter) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RENTER)) {

            statement.setString(1, renter.getFirstName());
            statement.setString(2, renter.getLastName());
            statement.setString(3, renter.getIdentityDocument());
            statement.setString(4, renter.getDriversLicense());
            statement.setObject(5, renter.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }


    @Override
    public boolean delete(Renter renter) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_RENTER)) {

            ps.setObject(1, renter.getId());
            return ps.executeUpdate() > 0;
        }
    }

    private Renter mapToRenter(ResultSet rs) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String identityDocument = rs.getString("identity_document");
        String driversLicense = rs.getString("drivers_license");

        return new Renter(id, firstName, lastName, identityDocument, driversLicense);
    }
}
