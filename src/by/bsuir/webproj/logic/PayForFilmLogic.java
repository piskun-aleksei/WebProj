package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.dao.FilmsDAO;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class PayForFilmLogic {
    private final static Logger LOGGER = LogManager.getLogger(PayForFilmLogic.class);

    public static boolean payForFilm(int userId, int filmId, int filmPrice) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            if (dao.payForFilm(userId, filmId, filmPrice)) {
                LOGGER.debug("Film payed correctly");
                result = true;
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

    public static ArrayList<Film> findNonPayedFilms(int id) throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            ArrayList<Film> films;
            if ((films = dao.findNonPayedFilms(id)) != null) {
                LOGGER.debug("Non payed films found");
                return films;
            }
            return null;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        } finally {
            pool.closeConnection(conn);
        }
    }
}
