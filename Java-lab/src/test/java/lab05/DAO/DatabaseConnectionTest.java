package lab05.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {

    @BeforeAll
    public static void setUp() {
        // Встановлюємо початкові налаштування перед запуском тестів, якщо потрібно.
    }

    @Test
    public void testConnection() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            assertNotNull(connection, "З'єднання не було успішно відкрито!");
            assertTrue(connection.isValid(2), "З'єднання не є валідним!");
            connection.createStatement().executeQuery("SELECT 1");

        } catch (SQLException e) {
            fail("Помилка при з'єднанні з базою даних: " + e.getMessage());
        }
    }
}
