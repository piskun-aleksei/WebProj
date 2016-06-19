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

import java.util.ArrayList;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class OrderFilmLogic {
    private final static Logger LOGGER = LogManager.getLogger(OrderFilmLogic.class);

    public static boolean orderFilm(Film film, int id) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            if (dao.orderFilm(film, id)) {
                LOGGER.debug("Film ordered");
                result = true;
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

    public static ArrayList<Film> findNonOrderedFilms(int id) throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            ArrayList<Film> films;
            if ((films = dao.findNonOrderedFilms(id)) != null) {
                LOGGER.debug("Non ordered films found");
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
