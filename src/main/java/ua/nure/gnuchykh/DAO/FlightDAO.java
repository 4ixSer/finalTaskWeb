package ua.nure.gnuchykh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.exception.Messages;
import ua.nure.gnuchykh.util.ConnectionPool;

/**
 * DAO for entity Flight.
 *
 * @author qny4ix
 *
 */
public class FlightDAO {

    private static final String SQL_SELECT_All_FLIGHT = "SELECT * FROM flight";
    private static final String SQL_SELECT_FLIGHT_BY_ID = "SELECT * FROM flight where id=?";
    private static final String SQL_SELECT_FLIGHT_BY_USER_ID = "SELECT * FROM flight where driver=?";
    private static final String SQL_SELECT_FLIGHT_BY_CAR = "SELECT * FROM flight where car=?";
    private static final String SQL_INSERT_FLIGHT = "INSERT INTO flight (date, status, driver, car, dispatcher, note) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_FLIGHT = "DELETE FROM flight WHERE id=?";
    private static final String SQL_UPDARE_FLIGHT = "UPDATE flight SET date=?, status=?, driver=?, car=?, dispatcher=?, note=? WHERE id=?";

    private Connection connector;

    /**
     * Method for searching all entities in the database.
     */
    public List<Flight> findAll() throws DBException {

        List<Flight> flights = new ArrayList<Flight>();
        connector = null;
        Statement st = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            st = connector.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_All_FLIGHT);

            while (resultSet.next()) {
                Flight flight = createFlight(resultSet);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FLIGHT, e);
        } finally {
            ConnectionPool.close(st);
            ConnectionPool.close(connector);
        }
        return flights;
    }

    /**
     * A method for creating an entity using the data retrieved from the ResultSet query.
     */
    private Flight createFlight(final ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();

        flight.setNamberFlight(resultSet.getInt("id"));
        flight.setDate(Flight.fromValueDate(resultSet.getString("date")));
        flight.setStatus(Status.fromValue(resultSet.getInt("status")));

        // получение драйвера  id
        flight.setDriver(resultSet.getInt("driver"));

        // получение диспечера  id
        flight.setDispatcher(resultSet.getInt("dispatcher"));

        // полученеи выбраной машины
        flight.setCar(resultSet.getInt("car"));

        flight.setNote(resultSet.getString("note"));
        return flight;
    }

    /**
     * A method for searching for an entity by its indifier.
     */
    public Flight findEntityById(final int id) throws DBException {
        connector = null;
        Flight flight = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_FLIGHT_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                flight = createFlight(resultSet);

            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHT_BY_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return flight;
    }

    /**
     * The method for removing the entity by its indifier.
     */
    public boolean delete(final int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_DELETE_FLIGHT);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_DELETE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for removing an entity from a database by its instance.
     */
    public boolean delete(final Flight entity) throws DBException {
        return delete(entity.getNamberFlight());
    }

    /**
     * The method creates an entity in the database.
     */
    public boolean create(final Flight entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_INSERT_FLIGHT);
            ps.setString(1, entity.toStringDate());
            ps.setInt(2, entity.getStatus().value());
            ps.setInt(3, entity.getDriver());
            ps.setInt(4, entity.getCar());
            ps.setInt(5, entity.getDispatcher());
            ps.setString(6, entity.getNote());

            ps.execute();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CREATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for updating entity data in a database by an instance of the entity.
     */
    public Flight update(final Flight entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_UPDARE_FLIGHT);
            ps.setString(1, entity.toStringDate());
            ps.setInt(2, entity.getStatus().value());
            ps.setDouble(3, entity.getDriver());
            ps.setDouble(4, entity.getCar());
            ps.setDouble(5, entity.getDispatcher());
            ps.setString(6, entity.getNote());
            ps.setInt(7, entity.getNamberFlight());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    /**
     * Search for flights by user ID.
     */
    public List<Flight> findEntityByDriverId(final int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        List<Flight> flights = new ArrayList<Flight>();

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_FLIGHT_BY_USER_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Flight flight = createFlight(resultSet);
                flights.add(flight);

            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_BY_DRIVER, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return flights;
    }

    /**
     *  Flight search by ID machines.
     */
    public List<Flight> findEntityByCarId(final int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        List<Flight> flights = new ArrayList<Flight>();

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_FLIGHT_BY_CAR);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Flight flight = createFlight(resultSet);
                flights.add(flight);

            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_BY_CAR, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return flights;
    }

    /**
     * Method for creating a voyage.
     * At the same time, the status of the machine is checked and changed. And the application is deleted.
     * If one of these operations does not pass, then all roll back.
     */
    public boolean create(final Flight entity, final Integer idCar, final Integer idRequest) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        String SQL_DELETE_REQUEST = "DELETE FROM request WHERE id=?";
        String SQL_UPDETE_CAR = "UPDATE car SET statusCar=? WHERE id=?";
        String SQL_SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id=?";


        try {
            connector = ConnectionPool.getInstance().getConnection();
            connector.setAutoCommit(false);

            ps = connector.prepareStatement(SQL_DELETE_REQUEST);
            ps.setInt(1, idRequest);
            ps.execute();

            ps = connector.prepareStatement(SQL_SELECT_CAR_BY_ID);
            ps.setInt(1, idCar);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("statusCar") == 2) {
                    throw new SQLException();
                }
            }

            ps = connector.prepareStatement(SQL_UPDETE_CAR);
            ps.setInt(1, ua.nure.gnuchykh.entity.cars.Status.USED.value());
            ps.setInt(2, idCar);
            ps.execute();

            ps = connector.prepareStatement(SQL_INSERT_FLIGHT);
            ps.setString(1, entity.toStringDate());
            ps.setInt(2, entity.getStatus().value());
            ps.setInt(3, entity.getDriver());
            ps.setInt(4, entity.getCar());
            ps.setInt(5, entity.getDispatcher());
            ps.setString(6, entity.getNote());
            ps.execute();

            connector.commit();
        } catch (SQLException e) {
            ConnectionPool.rollback(connector);
            throw new DBException(Messages.ERR_CANNOT_CREATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for updating the status of the application and freeing the machine.
     * If one of these operations does not pass, then all roll back.
     */
    public Flight update(final Flight entity, final Integer idCar, final ua.nure.gnuchykh.entity.cars.Status status, final String comments) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        String SQL_UPDETE_CAR = "UPDATE car SET statusCar=?, comments=? WHERE id=?";
        String SQL_UPDARE_STATUS_FLIGHT = "UPDATE flight SET status=? , note=? WHERE id=?";

        try {
            connector = ConnectionPool.getInstance().getConnection();
            connector.setAutoCommit(false);

            ps = connector.prepareStatement(SQL_UPDARE_STATUS_FLIGHT);
            ps.setInt(1, Status.CLOSED.value());
            ps.setString(2, comments);
            ps.setInt(3, entity.getNamberFlight());
            ps.executeUpdate();

            ps = connector.prepareStatement(SQL_UPDETE_CAR);
            ps.setInt(1, status.value());
            ps.setString(2, comments);
            ps.setInt(3, idCar);
            ps.executeUpdate();

            connector.commit();
        } catch (SQLException e) {
            ConnectionPool.rollback(connector);
            System.err.println(e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

}
