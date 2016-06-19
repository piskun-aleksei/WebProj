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
public class LoginLogic {
    private final static Logger LOGGER = LogManager.getLogger(LoginLogic.class);
    private static User user;

    public static String login(String login, String password) throws LogicException {

        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            user = dao.findEntityByString(login);
            if (user == null) {
                return null;
            }
            if (user.getLogin().equals(login) && user.getPassword().equals(CryptingHashHandler.getHash(password))) {
                LOGGER.debug(login + " logged in");
                return login;
            } else {
                return null;
            }
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        } finally {
            pool.closeConnection(conn);
        }
    }

    public static boolean isAdmin() {
        return user.getIsAdmin();
    }

    public static int getUserId() {
        return user.getId();
    }

    public static String getUserPassword() {
        return user.getPassword();
    }
}
