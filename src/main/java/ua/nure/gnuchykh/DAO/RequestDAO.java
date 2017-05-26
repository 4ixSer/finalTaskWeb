package ua.nure.gnuchykh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.ConnectionPool;

public class RequestDAO {
    private static final String SQL_INSERT_REQUEST = "INSERT INTO request (ownerRequest, dataRequest, dataDeparture, car_type, carrying_car, amount_car, enginePower, status, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_All_REQUEST = "SELECT * FROM request";
    private static final String SQL_SELECT_REQUEST_BY_ID = "SELECT * FROM request where id=?";
    private static final String SQL_SELECT_REQUEST_BY_USER_ID = "SELECT * FROM request where ownerRequest=?";
    private static final String SQL_DELETE_REQUEST = "DELETE FROM request WHERE id=?";
    private static final String SQL_UPDETE_REQUEST = "UPDATE request SET ownerRequest=?, dataRequest=?, dataDeparture=?, car_type=?, carrying_car=?, amount_car=?, enginePower=?, status=?, note=? WHERE id=?";

    private Connection connector;

    public RequestDAO() {

    }

    public List<Request> findAll() {
        connector = null;
        List<Request> requests = new ArrayList<Request>();

        Statement st = null;
        try {
            connector = ConnectionPool.getConnection();
            st = connector.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_All_REQUEST);

            while (resultSet.next()) {
                Request request = createRequest(resultSet);
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(st);
            ConnectionPool.close(connector);
        }

        return requests;
    }

    private Request createRequest(ResultSet resultSet) throws SQLException {
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

    public Request findEntityById(int id) {
        connector = null;
        Request request = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_REQUEST_BY_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                request = createRequest(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);

        }

        return request;

    }


    public  List<Request> findEntityByUserId(int id) {
        connector = null;
        PreparedStatement ps = null;

        List<Request> requests = new ArrayList<Request>();

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_REQUEST_BY_USER_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Request request = createRequest(resultSet);
                requests.add(request);

            }

        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);

        }

        return requests;

    }

    public boolean delete(int id) {
        // Данный метод мне пока не нужен.
        throw new IllegalArgumentException();
    }

    public boolean delete(Request entity) {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_DELETE_REQUEST);
            ps.setInt(1, entity.getNamberRequest());
            ps.execute();
        } catch (SQLException e) {
            // TODO Сюда систему логирования
            System.err.println("SQL exception: " + e);
            return false;
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    public boolean create(Request entity) {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getConnection();
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
            ps.close();
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e);
            return false;// TODO Сюда систему логирования
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    public Request update(Request entity) {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
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

}
