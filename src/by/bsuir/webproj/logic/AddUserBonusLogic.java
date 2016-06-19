package by.bsuir.webproj.logic;

import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class AddUserBonusLogic {
    private final static Logger LOGGER = LogManager.getLogger(AddUserBonusLogic.class);

    public static boolean addBonus(int id, int bonus) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            UserDAO dao = new UserDAO(conn);
            if (dao.addBonus(id, bonus)) {
                result = true;
                LOGGER.debug("Bonus added");
            }
            return result;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        }  finally {
            pool.closeConnection(conn);
        }
    }
}
