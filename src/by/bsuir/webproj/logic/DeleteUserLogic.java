package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.FilmsDAO;
import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.pattern.ArrayPatternConverter;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class DeleteUserLogic {
    private final static Logger LOGGER = LogManager.getLogger(DeleteUserLogic.class);

    public static boolean deleteUser(String login) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            if (dao.deleteEntity(login)) {
                result = true;
                LOGGER.debug("User removed");
            }
            return result;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        } finally {
            pool.closeConnection(conn);
        }
    }

    public static ArrayList<User> findNonAdmins() throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            ArrayList<User> users;
            if ((users = dao.findNonAdminEntities()) != null) {
                LOGGER.debug("Users found");
                return users;
            }
            return null;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        }  finally {
            pool.closeConnection(conn);
        }
    }
}
