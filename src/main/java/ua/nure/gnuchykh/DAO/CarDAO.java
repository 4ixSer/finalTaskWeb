package ua.nure.gnuchykh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.util.ConnectionPool;


public class CarDAO {
    private static final String SQL_SELECT_ALL_CAR = "SELECT * FROM car";
    private static final String SQL_INSERT_CAR = "INSERT INTO car (namber, type, carryingCar, amountCar, enginePower, statusCar, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_CAR = "DELETE FROM car WHERE id=?";
    private static final String SQL_SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id=?";
    private static final String SQL_SELECT_CAR_BY_NAMBER = "SELECT * FROM car WHERE namber=?";
    private static final String SQL_UPDETE_CAR ="UPDATE car SET namber=?, type=?, carryingCar=?, amountCar=?, enginePower=?, statusCar=?, comments=? WHERE id=?";

    private Connection connector;

    public CarDAO() {

    }



    public List<Car> findAll() {
        connector = null;
        List<Car> cars = new ArrayList<Car>();
        Statement statement = null;

        try {
            connector = ConnectionPool.getConnection();
            statement = connector.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CAR);
            while(resultSet.next()) {
                Car car =createCar(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
         // TODО логирование
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                // TODО логирование
                e.printStackTrace();
            }
        }
        return cars;
    }


    public Car findEntityById(int id) {
        connector = null;
        Car car = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return car;
    }


    public boolean delete(int id) {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_DELETE_CAR);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            // TODО логирование
            System.err.println("SQL exception: " + e);
            return false;
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Метод для удаления машины с БД.
     */

    public boolean delete(Car entity) {
        return delete(entity.getId());

    }

    /**
     * Метод создает машину.
     */

    public boolean create(Car entity) {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_INSERT_CAR);

            ps.setString(1, entity.getNamber());
            ps.setInt(2, entity.getType().value());
            ps.setDouble(3, entity.getCarryingCar());
            ps.setDouble(4, entity.getAmountCar());
            ps.setDouble(5, entity.getEnginePower());
            ps.setInt(6, entity.getStatusCar().value());
            ps.setString(7, entity.getComments());
            ps.execute();

        } catch (SQLException e) {
            // TODО логирование
            e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    private Car createCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getInt("id"));
        car.setNamber(resultSet.getString("namber"));
        car.setType(TYPE.fromValue(resultSet.getInt("type")));
        car.setCarryingCar(resultSet.getDouble("carryingCar"));
        car.setAmountCar(resultSet.getDouble("amountCar"));
        car.setEnginePower(resultSet.getDouble("enginePower"));
        car.setStatusCar(Status.fromValue(resultSet.getInt("statusCar")));
        car.setComments(resultSet.getString("comments"));

        return car;
    }


    public Car update(Car entity) {
        PreparedStatement ps = null;
        connector = null;
        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_UPDETE_CAR);
            ps.setString(1, entity.getNamber());
            ps.setInt(2, entity.getType().value());
            ps.setDouble(3, entity.getCarryingCar());
            ps.setDouble(4, entity.getAmountCar());
            ps.setDouble(5, entity.getEnginePower());
            ps.setInt(6, entity.getStatusCar().value());
            ps.setString(7, entity.getComments());

            ps.setInt(8, entity.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO Сюда систему логирования
            return null;
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    public Car findEntityByNamber(String namber) {

        Car car = null;
        PreparedStatement ps = null;
        connector = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_NAMBER);
            ps.setString(1, namber);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);

        }
        return car;
    }

}
