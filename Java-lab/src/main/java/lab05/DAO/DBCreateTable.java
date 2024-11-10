package lab05.DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DBCreateTable {

    private static final String CREATE_TABLE_CAR = """
        CREATE TABLE Car (
            id UUID PRIMARY KEY,
            manufacturer VARCHAR(50),
            brand VARCHAR(50),
            type VARCHAR(50),
            color VARCHAR(30),
            cost_per_day DECIMAL(10, 2),
            vin_code VARCHAR(20) UNIQUE,
            release_year INT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    private static final String CREATE_TABLE_RENTER = """
        CREATE TABLE Renter (
            id UUID PRIMARY KEY,
            first_name VARCHAR(50),
            last_name VARCHAR(50),
            identity_document VARCHAR(50),
            drivers_license VARCHAR(50)
        );
    """;

    private static final String CREATE_TABLE_PAYMENT = """
        CREATE TABLE Payment (
            id UUID PRIMARY KEY,
            payment_method VARCHAR(20),
            amount DECIMAL(10, 2)
        );
    """;

    private static final String CREATE_TABLE_AGREEMENT = """
        CREATE TABLE Agreement (
            id UUID PRIMARY KEY,
            car_id UUID REFERENCES Car(id),
            renter_id UUID REFERENCES Renter(id),
            start_date DATE,
            end_date DATE,
            payment_id UUID REFERENCES Payment(id)
        );
    """;

    public static void createTables() {
        try (Connection connection = DatabaseConnection.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(CREATE_TABLE_CAR);
            statement.execute(CREATE_TABLE_RENTER);
            statement.execute(CREATE_TABLE_PAYMENT);
            statement.execute(CREATE_TABLE_AGREEMENT);

            System.out.println("Таблиці створені успішно!");
        } catch (SQLException e) {
            System.out.println("Помилка при створенні таблиць: " + e.getMessage());
        }
    }
}
