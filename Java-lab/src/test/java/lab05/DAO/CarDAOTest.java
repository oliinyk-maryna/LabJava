package lab05.DAO;

import lab05.Models.Car;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarDAOTest {

    private CarDAO carDAO;
    private Car testCar;

    @BeforeEach
    void setUp() throws SQLException {
        carDAO = new CarDAO();
        testCar = new Car("Toyota", "Camry", "Sedan", "White", 50.0, "VIN1234567890", 2022);
        carDAO.create(testCar);
    }

    @AfterEach
    void tearDown() throws SQLException {
        carDAO.delete(testCar);
    }

    @Test
    void testGetAll() throws SQLException {
        List<Car> cars = carDAO.getAll();
        assertNotNull(cars);
        assertTrue(cars.size() > 0, "The car list should not be empty");
    }

    @Test
    void testGetById() throws SQLException {

        Optional<Car> car = carDAO.getById(testCar.getId());
        assertTrue(car.isPresent(), "Car should be found");
        assertEquals(testCar.getVinCode(), car.get().getVinCode(), "The VIN codes should match");
    }

    @Test
    void testCreate() throws SQLException {
        Car newCar = new Car("Honda", "Accord", "Sedan", "Black", 55.0, "VIN0987654321", 2021);
        assertTrue(carDAO.create(newCar), "The car should be created successfully");
        carDAO.delete(newCar);
    }

    @Test
    void testUpdate() throws SQLException {
        testCar.setColor("Red");
        assertTrue(carDAO.update(testCar), "The car update should be successful");

        Optional<Car> updatedCar = carDAO.getById(testCar.getId());
        assertTrue(updatedCar.isPresent(), "Updated car should be found");
        assertEquals("Red", updatedCar.get().getColor(), "The color should be updated to Red");
    }

    @Test
    void testDelete() throws SQLException {
        Car newCar = new Car("Nissan", "Altima", "Sedan", "Gray", 60.0, "VIN1122334455", 2020);
        carDAO.create(newCar);
        assertTrue(carDAO.delete(newCar), "The car should be deleted successfully");

        Optional<Car> deletedCar = carDAO.getById(newCar.getId());
        assertFalse(deletedCar.isPresent(), "Deleted car should not be found");
    }
}