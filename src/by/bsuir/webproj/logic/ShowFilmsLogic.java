package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.FilmsDAO;
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
import java.util.ArrayList;

/**
 * Created by Алексей on 24.04.2016.
 */
public class ShowFilmsLogic {
    private final static Logger LOGGER = LogManager.getLogger(ShowFilmsLogic.class);

    public static ArrayList<Film> searchForFilms() throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            FilmsDAO dao = new FilmsDAO(conn);
            ArrayList<Film> films = dao.findAll();
            LOGGER.debug("Films found");
            return films;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        } finally {
            pool.closeConnection(conn);
        }
    }
}
