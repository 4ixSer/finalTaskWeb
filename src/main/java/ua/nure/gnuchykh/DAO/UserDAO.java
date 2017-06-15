package ua.nure.gnuchykh.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.exception.Messages;
import ua.nure.gnuchykh.util.ConnectionPool;

/**
 * DAO for entity Ñar.
 *
 * @author qny4ix
 *
 */
public class UserDAO {
    private static final String SQL_SELECT_All_USER = "SELECT * FROM user";
    private static final String SQL_SELECT_USER_BY_id = "SELECT * FROM user WHERE id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_INSERT_USER = "INSERT INTO user (login, password, name, email, role) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_UPDETE_USER = "UPDATE user SET login=?, password=?, name=?, email=?, role=? WHERE id=?";

    private Connection connector;

    /**
     * Method for searching all entities in the database.
     */
    public List<User> findAll() throws DBException {
        connector = null;
        List<User> users = new ArrayList<User>();
        Statement st = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            st = connector.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_All_USER);

            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, e);
        } finally {
            ConnectionPool.close(st);
            ConnectionPool.close(connector);
        }
        return users;
    }

    /**
     * A method for creating an entity using the data retrieved from the ResultSet query.
     */
    private User createUser(final ResultSet resultSet) throws SQLException {

        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setType(ClientType.fromValue(resultSet.getInt("role")));

        return user;
    }

    /**
     * A method for searching for an entity by its indifier.
     */
    public User findEntityById(final int id) throws DBException {
        connector = null;
        User user = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_USER_BY_id);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return user;
    }

    /**
     * The method for removing the entity by its indifier.
     */
    public boolean delete(final int id) throws DBException {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_DELETE_USER, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for removing an entity from a database by its instance.
     */
    public boolean delete(final User entity) throws DBException {
        return delete(entity.getId());
    }

    /**
     * The method creates an entity in the database.
     */
    public boolean create(final User entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());
            ps.setInt(5, entity.getType().value());
            ps.execute();

        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CREATE_USER, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return true;
    }

    /**
     * Method for updating entity data in a database by an instance of the entity.
     */
    public User update(final User entity) throws DBException {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_UPDETE_USER);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());

            ps.setInt(5, entity.getType().value());
            ps.setInt(6, entity.getId());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return entity;
    }

    /**
* The method calls from the database the right person by his login.
     *
     * @param login
     * @return
     * @throws DBException
     */
    public User findEntityByLogin(final String login) throws DBException {
        connector = null;
        User user = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getInstance().getConnection();
            ps = connector.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_FIND_USER_BY_ID, e);
        } finally {
            ConnectionPool.close(ps);
            ConnectionPool.close(connector);
        }
        return user;
    }

}
