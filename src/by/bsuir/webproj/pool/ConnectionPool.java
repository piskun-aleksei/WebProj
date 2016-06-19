package by.bsuir.webproj.pool;

import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Алексей on 12.04.2016.
 */
public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<WrapperConnection> connections;
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static Properties property;
    private static ReentrantLock lock = new ReentrantLock();
    private static int maxPool;
    private static int poolSize;
    private static AtomicBoolean connectionsToOffer = new AtomicBoolean(true);
    private static final int CLOSE_WAITING_TIME = 2;

    private ConnectionPool() {
        property = new Properties();
        try {
            DriverManager.registerDriver(new Driver());
            LOGGER.debug("Driver set");
            ResourceBundle resource = ResourceBundle.getBundle("resources.database");
            String url = resource.getString("url");
            maxPool = Integer.parseInt(resource.getString("pool"));
            property.put("user", resource.getString("login"));
            property.put("password", resource.getString("password"));
            property.put("autoReconnect", resource.getString("autoreconnect"));
            property.put("characterEncoding", resource.getString("encoding"));
            property.put("useUnicode", resource.getString("unicode"));
            connections = new ArrayBlockingQueue<>(maxPool);
            poolSize = 0;
            for (int i = 0; i < maxPool; i++) {
                Connection conn = DriverManager.getConnection(url, property);
                WrapperConnection connection = new WrapperConnection(conn);
                connections.offer(connection);
                poolSize++;
            }
            if (connections.size() != maxPool) {
                throw new RuntimeException("Connections was not created");
            }
            LOGGER.debug("Pool initialized");
        } catch (MissingResourceException e) {
            throw new RuntimeException("Missing resource ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception ", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!created.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    created.set(true);
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;

    }

    public WrapperConnection getConnection() throws ConnectionPoolException {
        if (connectionsToOffer.get()) {
            try {
                LOGGER.debug("Connection taken");
                return connections.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Interrupted exc ", e);
            }
        }
        return null;
    }

    public void closeConnection(WrapperConnection connection) {

        try {
            connections.put(connection);//
            LOGGER.debug("Connection returned");
        } catch (InterruptedException e) {
            LOGGER.error("WrapperConnection exc");
        }
    }

    public int getSize(){
        return poolSize;
    }

    public void closePool() {
        connectionsToOffer.set(false);
        for (int i = 0; i < maxPool; i++) {
            try {
                connections.take().closeConnection();
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted exc");
            } catch (WrapperConnectionException e) {
                LOGGER.error("WrapperConnection exc");
            }
        }
    }

    @PreDestroy
    private void releasePool() {
        connectionsToOffer.set(false);
        try {
            TimeUnit.SECONDS.sleep(CLOSE_WAITING_TIME);
            for (WrapperConnection connection : connections) {
                try {
                    connection.closeConnection();
                } catch (WrapperConnectionException e) {
                    LOGGER.error("Connection Exception");
                }
            }
        } catch (InterruptedException ex) {
            LOGGER.warn("Release connection exception", ex);
        }
    }

}
