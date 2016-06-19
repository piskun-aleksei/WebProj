package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.*;
import by.bsuir.webproj.handler.CryptingHashHandler;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Алексей on 24.04.2016.
 */
public class RegistrationLogic {
    private final static Logger LOGGER = LogManager.getLogger(RegistrationLogic.class);

    public static boolean registrate(String login, String password, String name, String surname) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            if (dao.addEntity(new User(login, CryptingHashHandler.getHash(password), 0, 1, false, name, surname))) {
                result = true;
                LOGGER.debug("User registered");
            }
            return result;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        }finally {
            pool.closeConnection(conn);
        }
    }
}
