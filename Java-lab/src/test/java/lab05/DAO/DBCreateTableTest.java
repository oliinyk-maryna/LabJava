package lab05.DAO;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbCreateTableTest {

    @Test
    void createTables() throws SQLException {
        DBCreateTable.createTables();
        assertTrue(true);
    }
}