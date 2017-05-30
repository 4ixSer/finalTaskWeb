package ua.nure.gnuchykh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.exception.Messages;
import ua.nure.gnuchykh.util.ConnectionPool;

public class RequestDAO {
    private static final String SQL_INSERT_REQUEST = "INSERT INTO request (ownerRequest, dataRequest, dataDeparture, car_type, carrying_car, amount_car, enginePower, status, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_All_REQUEST = "SELECT * FROM request";
    private static final String SQL_SELECT_REQUEST_BY_ID = "SELECT * FROM request where id=?";
    private static final String SQL_SELECT_REQUEST_BY_USER_ID = "SELECT * FROM request where ownerRequest=?";
    private static final String SQL_DELETE_REQUEST = "DELETE FROM request WHERE id=?";
    private static final String SQL_UPDETE_REQUEST = "UPDATE request SET ownerRequest=?, dataRequest=?, dataDeparture=?, car_type=?, carrying_car=?, amount_car=?, enginePower=?, status=?, note=? WHERE id=?";
    private static final String SQL_SELECT_FIRST_REQUEST = "SELECT * FROM request where dataDeparture > ? and status  = 7  LIMIT 1";
    private Connection connector;

    public List<Request> findAll() throws DBException {
        connector = null;
        List<Request> requests = new ArrayList<Request>();

        Statement st = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            st = connector.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_All_REQUEST);

            while (resultSet.next()) {
                Request request = createRequest(resultSet);
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_REQUEST, e);
        } finally {
            ConnectionPool.close(st);
            ConnectionPool.close(connector);
        }
        return requests;
    }

    private Request createRequest(ResultSet resultSet) throws SQLException, DBException {
        Request request = new Request();

        request.setNamberRequest(resultSet.getInt("id"));
        request.setStatus(Status.fromValue(resultSet.getInt("status")));
        request.setNote(resultSet.getString("note"));

        // Cушьность временной машины взятой с заявки Важный пользователю ёё
        // характеристики
        request.setType(TYPE.fromValue(resultSet.getInt("car_type")));
        request.setCarryingCar(resultSet.getDouble("carrying_car"));
        request.setAmountCar(resultSet.getDouble("amount_car"));
        request.setEnginePower(resultSet.getDouble("enginePower"));

        // получение юзера создавшего запрос
        User driver = new UserDAO().findEntityById(resultSet.getInt("ownerRequest"));
        request.setOwnerRequest(driver.getId());

        // Преобразлование и запись времени в запрос
        request.setDataRequest(Request.fromValueDataRequest(resultSet.getString("dataRequest")));
        request.setDataDeparture(Request.fromValueDataDeparture(resultSet.getString("dataDeparture")));
        return request;
    }

    public Request findEntityById(int id) throws DBException {
        connector = null;
        Request request = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_REQUEST_BY_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                request = createRequest(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUEST_BY_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return request;
    }

    public List<Request> findEntityByUserId(int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        List<Request> requests = new ArrayList<Request>();

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_REQUEST_BY_USER_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Request request = createRequest(resultSet);
                requests.add(request);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUEST_BY_USER_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return requests;
    }

    public boolean delete(int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_DELETE_REQUEST);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_DELETE_REQUEST, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    public boolean delete(Request entity) throws DBException {
        return delete(entity.getNamberRequest());
    }

    public boolean create(Request entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_INSERT_REQUEST);

            ps.setInt(1, entity.getOwnerRequest());

            ps.setString(2, entity.toStringDataRequest());
            ps.setString(3, entity.toStringDataDeparture());

            // тут мы вытягиевам предпологаемые данные для машины
            ps.setInt(4, entity.getType().value());
            ps.setDouble(5, entity.getCarryingCar());
            ps.setDouble(6, entity.getAmountCar());
            ps.setDouble(7, entity.getEnginePower());
            ps.setInt(8, entity.getStatus().value());
            ps.setString(9, entity.getNote());
            ps.execute();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CREATE_REQUEST, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    public Request update(Request entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_UPDETE_REQUEST);

            ps.setInt(1, entity.getOwnerRequest());
            ps.setString(2, entity.toStringDataRequest());
            ps.setString(3, entity.toStringDataDeparture());
            ps.setInt(4, entity.getType().value());

            // обнавляем запрашиваемые типы машын
            ps.setDouble(5, entity.getCarryingCar());
            ps.setDouble(6, entity.getAmountCar());

            ps.setDouble(7, entity.getEnginePower());
            ps.setInt(8, entity.getStatus().value());
            ps.setString(9, entity.getNote());

            ps.setInt(10, entity.getNamberRequest());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_REQUEST, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    public Request findFirstRequest(LocalDateTime time) throws DBException {
        connector = null;
        Request request = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_FIRST_REQUEST);
            // TODO возможно тут бред

            ps.setString(1, time.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss")));

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                request = createRequest(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_FIRST_REQUEST, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return request;
    }

}
