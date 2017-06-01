package ua.nure.gnuchykh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.exception.Messages;

public class ConnectionPool {

    private static final String DATASOURCE_NAME = "jdbc/autobase";
    private DataSource dataSource;

    private static ConnectionPool instance;

    public static synchronized ConnectionPool getInstance() throws DBException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private ConnectionPool() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
        }
    }

    public Connection getConnection() throws DBException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
        return connection;
    }

    public static void close(Statement st) throws DBException {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CLOSE_STATEMENT, e);
        }
    }

    public static void close(PreparedStatement ps) throws DBException {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_CLOSE_PREPARED_STATEMENT, e);
        }
    }

    public static void close(Connection connection) throws DBException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
    }

    public static void rollback(Connection con) throws DBException {
        if (con != null) {
                try {
                        con.rollback();
                } catch (SQLException e) {
                    throw new DBException(Messages.ERR_ROLLBACK, e);
                }
        }
}

}