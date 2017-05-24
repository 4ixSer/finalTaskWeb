package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.ClientType;
import com.entity.User;
import com.util.ConnectionPool;

public class UserDAO {
    private static final String SQL_SELECT_All_USER = "SELECT * FROM user";
    private static final String SQL_SELECT_USER_BY_id = "SELECT * FROM user WHERE id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_INSERT_USER = "INSERT INTO user (login, password, name, email, role) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE id=?";
    private static final String SQL_UPDETE_USER = "UPDATE user SET login=?, password=?, name=?, email=?, role=? WHERE id=?";

    private Connection connector;

    public UserDAO() {

    }

    /**
     * ����� ��� ��������� ������ ���� ������������.
     */

    public List<User> findAll() {
        connector = null;
        List<User> users = new ArrayList<User>();
        Statement st = null;

        try {
            connector = ConnectionPool.getConnection();
            st = connector.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_All_USER);

            while (resultSet.next()) {

                User user = createUser(resultSet);
                users.add(user);

            }

        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO ���� ������� �����������
        } finally {
            close(st);
            close(connector);
        }
        return users;
    }

    /**
     * ����� ������� ��������� ���� �� ���������� ������� �������� � resultSet�.
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */

    private User createUser(ResultSet resultSet) throws SQLException {

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
     * ����� ��� ��������� ����� �� ��� ID
     */

    public User findEntityById(int id) {
        connector = null;
        User user = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_USER_BY_id);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO ���� ������� �����������
        } finally {
            close(ps);
            close(connector);
        }
        return user;
    }

    public boolean delete(int id) {
        // ������ ����� ��� ���� �� �����.
        throw new IllegalArgumentException();
    }

    /**
     * ����� ������� ����� � ���� ������.
     */

    public boolean delete(User entity) {
        connector = null;
        PreparedStatement ps = null;
        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1, entity.getId());
            ps.execute();

        } catch (SQLException e) {
            // TODO ���� ������� �����������
            System.err.println("SQL exception: " + e);
            return false;
        } finally {
            close(ps);
            close(connector);
        }

        return true;
    }

    /**
     * ����� ������� ��������� User � ���� ������.
     */

    public boolean create(User entity) {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_INSERT_USER);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());

            ps.setInt(5, entity.getType().value());
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ���� ������� �����������
            return false;
        } finally {
            close(ps);
            close(connector);
        }
        return true;
    }
    // "UPDATE user SET login=?, password=?, name=?, email=?, role=? WHERE
    // id=?"

    public User update(User entity) {
        connector = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_UPDETE_USER);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getEmail());
            // TODO ��� �������� �������
            ps.setInt(5, 3);
            ps.setInt(6, entity.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO ���� ������� �����������
            return null;
        } finally {
            close(ps);
            close(connector);
        }
        return entity;
    }

    /**
     * ����� �������� � ���� ������ ������� �������� �� ��� ������.
     *
     * @param login
     * @return
     */
    public User findEntityByLogin(String login) {
        connector = null;
        User user = null;
        PreparedStatement ps = null;

        try {
            connector = ConnectionPool.getConnection();
            ps = connector.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            System.err.println(" userDAO lt connection");
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Error createStatement: " + e);
            // TODO ���� ������� �����������
        } finally {
            close(ps);
            close(connector);

        }
        return user;

    }

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            // ��� � ������������� �������� Statement
        }
    }

    public void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            // ��� � ������������� PreparedStatement
        }
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // ��������� ����������, �.�. ���������� ������ ����
        }
    }

}
