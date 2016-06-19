package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.FilmsDAO;
import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.handler.CryptingHashHandler;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Алексей on 04.05.2016.
 */
public class AddFilmLogic {
    private final static Logger LOGGER = LogManager.getLogger(AddFilmLogic.class);

    public static boolean addFilm(String name, String desc, int ageRating, int price) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            if (dao.addEntity(new Film(name, desc, ageRating, price))) {
                result = true;
                LOGGER.debug("Film added");
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
