/*package lab05.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Метод для додавання нового автомобіля
    public void addCar(Car car) {
        String sql = "INSERT INTO cars (manufacturer, brand, type, color, costPerDay, vinCode, releaseYear) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getManufacturer());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getType());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setDouble(5, car.getCostPerDay());
            preparedStatement.setString(6, car.getVinCode());
            preparedStatement.setInt(7, car.getReleaseYear());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для отримання всіх автомобілів
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection connection = DbManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                String brand = resultSet.getString("brand");
                String type = resultSet.getString("type");
                String color = resultSet.getString("color");
                double costPerDay = resultSet.getDouble("costPerDay");
                String vinCode = resultSet.getString("vinCode");
                int releaseYear = resultSet.getInt("releaseYear");

                Car car = new Car(manufacturer, brand, type, color, costPerDay, vinCode, releaseYear);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Метод для оновлення інформації про автомобіль
    public void updateCar(Car car) {
        String sql = "UPDATE cars SET manufacturer = ?, brand = ?, type = ?, color = ?, costPerDay = ?, releaseYear = ? WHERE vinCode = ?";

        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getManufacturer());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getType());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setDouble(5, car.getCostPerDay());
            preparedStatement.setInt(6, car.getReleaseYear());
            preparedStatement.setString(7, car.getVinCode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для видалення автомобіля за VIN кодом
    public void deleteCar(String vinCode) {
        String sql = "DELETE FROM cars WHERE vinCode = ?";

        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, vinCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/