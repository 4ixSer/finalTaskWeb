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
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.exception.Messages;
import ua.nure.gnuchykh.util.ConnectionPool;

/**
 * DAO for entity �ar.
 *
 * @author qny4ix
 *
 */
public class CarDAO {
    private static final String SQL_SELECT_ALL_CAR = "SELECT * FROM car";
    private static final String SQL_INSERT_CAR = "INSERT INTO car (namber, type, carryingCar, amountCar, enginePower, statusCar, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_CAR = "DELETE FROM car WHERE id=?";
    private static final String SQL_SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id=?";
    private static final String SQL_SELECT_CAR_BY_NAMBER = "SELECT * FROM car WHERE namber=?";
    private static final String SQL_UPDETE_CAR = "UPDATE car SET namber=?, type=?, carryingCar=?, amountCar=?, enginePower=?, statusCar=?, comments=? WHERE id=?";
    private static final String SQL_SELECT_CAR_BY_CHARACTERISTICS = "SELECT * FROM car where type = ? and statusCar = ? and carryingCar >= ? and amountCar >= ? and  enginePower >=?";
    private static final String SQL_SELECT_AVG_CAR = "SELECT  AVG(amountCar) avg_car FROM car where  statusCar = 3";
    private Connection connector;

    /**
     * Method for searching all entities in the database.
     */
    public List<Car> findAll() throws DBException {
        connector = null;
        List<Car> cars = new ArrayList<Car>();
        Statement statement = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            statement = connector.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CAR);
            while (resultSet.next()) {
                Car car = createCar(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_CAR, e);
        } finally {
            ConnectionPool.close(statement);
            ConnectionPool.close(connector);
        }
        return cars;
    }

    /**
     * A method for searching for an entity by its indifier.
     */
    public Car findEntityById(final int id) throws DBException {
        connector = null;
        Car car = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR_BY_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return car;
    }

    /**
     * The method for removing the entity by its indifier.
     */
    public boolean delete(final int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_DELETE_CAR);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_DELETE_CAR, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for removing an entity from a database by its instance.
     */
    public boolean delete(final Car entity) throws DBException {
        return delete(entity.getId());

    }

    /**
     * The method creates an entity in the database.
     */
    public boolean create(final Car entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
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
            throw new DBException(Messages.ERR_CANNOT_CREATE_CAR, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * A method for creating an entity using the data retrieved from the
     * ResultSet query.
     */
    private Car createCar(final ResultSet resultSet) throws SQLException {
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

    /**
     * Method for updating entity data in a database by an instance of the
     * entity.
     */
    public Car update(final Car entity) throws DBException {
        PreparedStatement ps = null;
        connector = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
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

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_CAR, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    /**
     * Search for the car by number.
     */
    public Car findEntityByNamber(final String namber) throws DBException {

        Car car = null;
        PreparedStatement ps = null;
        connector = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_NAMBER);
            ps.setString(1, namber);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_CAR_BY_NAMBER, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);

        }
        return car;
    }

    /**
     * Search for suitable machines by their characteristics.
     */
    public List<Car> findCarByсharacteristics(final TYPE type, final Status statusCar, final Double carryingCar, final Double amountCar,
            final Double enginePower) throws DBException {
        connector = null;
        List<Car> cars = new ArrayList<Car>();
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_CHARACTERISTICS);

            ps.setInt(1, type.value());
            ps.setInt(2, statusCar.value());
            ps.setDouble(3, carryingCar);
            ps.setDouble(4, amountCar);
            ps.setDouble(5, enginePower);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Car car = createCar(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_CAR_BY_CHARACTERISTICS, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return cars;
    }



 //   "SELECT  AVG(amountCar) avg_car FROM car where  statusCar = 3";
    public Double avgAmountCar() throws DBException {

        connector = null;
        List<Car> cars = new ArrayList<Car>();
        Statement statement = null;
        Double avg = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            statement = connector.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_AVG_CAR);
            while (resultSet.next()) {
                avg= resultSet.getDouble(1);

            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_AVG, e);
        } finally {
            ConnectionPool.close(statement);
            ConnectionPool.close(connector);
        }
        return avg;
    }
}
