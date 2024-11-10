package lab05.DAO;

import lab05.Interfaces.IDAO;
import lab05.Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarDAO implements IDAO<Car> {

    @Override
    public List<Car> getAll() throws SQLException {
        List<Car> cars = new ArrayList<>();

        String SELECT_ALL_QUERY = """
                SELECT *
                FROM Car
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                cars.add(mapToCar(rs));
            }
        }

        return cars;
    }

    @Override
    public Optional<Car> getById(UUID id) throws SQLException {
        String SELECT_QUERY = """
            SELECT *
            FROM Car
            WHERE id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToCar(rs));
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean create(Car car) throws SQLException {
        String INSERT_QUERY = """
                INSERT INTO Car
                (id, manufacturer, brand, type, color, cost_per_day, vin_code, release_year, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
            ps.setObject(1, car.getId());
            ps.setString(2, car.getManufacturer());
            ps.setString(3, car.getBrand());
            ps.setString(4, car.getType());
            ps.setString(5, car.getColor());
            ps.setDouble(6, car.getCostPerDay());
            ps.setString(7, car.getVinCode());
            ps.setInt(8, car.getReleaseYear());
            ps.setTimestamp(9, Timestamp.valueOf(car.getCreatedAt()));

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Car car) throws SQLException {
        String UPDATE_QUERY = """
                UPDATE Car
                SET manufacturer = ?, brand = ?, type = ?, color = ?, cost_per_day = ?, vin_code = ?, release_year = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, car.getManufacturer());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getType());
            ps.setString(4, car.getColor());
            ps.setDouble(5, car.getCostPerDay());
            ps.setString(6, car.getVinCode());
            ps.setInt(7, car.getReleaseYear());
            ps.setObject(8, car.getId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Car car) throws SQLException {
        String DELETE_QUERY = """
                DELETE FROM Car WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setObject(1, car.getId());

            return ps.executeUpdate() > 0;
        }
    }

    protected Car mapToCar(ResultSet rs) throws SQLException {
        Car car = new Car(
                rs.getString("manufacturer"),
                rs.getString("brand"),
                rs.getString("type"),
                rs.getString("color"),
                rs.getDouble("cost_per_day"),
                rs.getString("vin_code"),
                rs.getInt("release_year")
        );

        return car;
    }
}
