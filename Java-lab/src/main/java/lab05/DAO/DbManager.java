package lab05.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_rental"; // URL вашої бази даних
    private static final String JDBC_USERNAME = "root"; // Ваш логін
    private static final String JDBC_PASSWORD = "password"; // Ваш пароль

    // Метод для підключення до бази даних
    public static Connection getConnection() throws SQLException {
        try {
            // Завантаження драйвера (для MySQL або іншої СУБД)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    // Метод для закриття з'єднання з базою даних
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для перевірки підключення (для тестування)
    public static boolean isConnected() {
        try (Connection connection = getConnection()) {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
