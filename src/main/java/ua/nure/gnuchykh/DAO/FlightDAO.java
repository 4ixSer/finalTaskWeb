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

public class FlightDAO {

    private static final String SQL_SELECT_All_FLIGHT = "SELECT * FROM flight";
    private static final String SQL_SELECT_FLIGHT_BY_ID = "SELECT * FROM flight where id=?";
    private static final String SQL_SELECT_FLIGHT_BY_USER_ID = "SELECT * FROM flight where driver=?";
    private static final String SQL_INSERT_FLIGHT = "INSERT INTO flight (date, status, driver, car, dispatcher, note) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_FLIGHT = "DELETE FROM flight WHERE id=?";
    private static final String SQL_UPDARE_FLIGHT = "UPDATE flight SET date=?, status=?, driver=?, car=?, dispatcher=?, note=? WHERE id=?";

    private Connection connector;

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

    private Flight createFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();

        flight.setNamberFlight(resultSet.getInt("id"));
        flight.setDate(Flight.fromValueData(resultSet.getString("date")));
        flight.setStatus(Status.fromValue(resultSet.getInt("status")));

        // получение драйвера по id
        // User driver = new
        // UserDAO().findEntityById(resultSet.getInt("driver"));
        flight.setDriver(resultSet.getInt("driver"));

        // получение диспечера по id
        // User dispatcher = new
        // UserDAO().findEntityById(resultSet.getInt("dispatcher"));
        flight.setDispatcher(resultSet.getInt("dispatcher"));

        // полученеи выбраной машыны
        // Car car = new CarDAO().findEntityById(resultSet.getInt("car"));
        flight.setCar(resultSet.getInt("car"));

        flight.setNote(resultSet.getString("note"));
        return flight;
    }

    public Flight findEntityById(int id) throws DBException {
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

    public boolean delete(int id) throws DBException {
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

    public boolean delete(Flight entity) throws DBException {
        return delete(entity.getNamberFlight());
    }

    public boolean create(Flight entity) throws DBException {
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
            ps.close();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CREATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    public Flight update(Flight entity) throws DBException {
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
            ps.close();

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    public List<Flight> findEntityByDriverId(int id) throws DBException {
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
}
