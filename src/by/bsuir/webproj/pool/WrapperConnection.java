package by.bsuir.webproj.pool;

import by.bsuir.webproj.exception.WrapperConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Алексей on 12.04.2016.
 */
public class WrapperConnection {
    private Connection connection;
    private final static Logger LOGGER = LogManager.getLogger(WrapperConnection.class);

    WrapperConnection(Connection connection){
        this.connection = connection;
    }
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    public void setHoldability(int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    public void setCatalog(String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    public boolean isValid(int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    public void setSchema(String schema) throws SQLException {
        connection.setSchema(schema);
    }

    public void close() throws SQLException {
        connection.close();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    public void setTransactionIsolation(int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    public String nativeSQL(String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    public String getClientInfo(String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    public PreparedStatement getStatement(String preparedString) throws WrapperConnectionException {
        if (connection != null) {
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(preparedString);
            } catch (SQLException e) {
                throw  new WrapperConnectionException("SQL exception ", e);
            }
            if (statement != null) {
                LOGGER.debug("Statement taken");
                return statement;
            }
        }
        throw new WrapperConnectionException("connection or statement is null");
    }

    public void closeStatement(Statement statement) throws WrapperConnectionException {
        if (statement != null) {
            try {
                statement.close();
                LOGGER.debug("Statement closed");
            } catch (SQLException e) {
                throw new WrapperConnectionException("SQL exception ", e);
            }
        }
    }

    void closeConnection() throws WrapperConnectionException {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.debug("Connection closed");
            } catch (SQLException e) {
                throw new WrapperConnectionException("SQL exception ", e);
            }
        }
    }
}
