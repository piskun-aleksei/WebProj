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

/**
 * Created by Brotorias on 10.06.2016.
 */
public class ChangeLoginLogic {
    private final static Logger LOGGER = LogManager.getLogger(ChangeLoginLogic.class);

    public static boolean changeLogin(String login, String newLogin) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            if (dao.updateEntity(login, newLogin)) {
                result = true;
                LOGGER.debug("Login updated");
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
}
