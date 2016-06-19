package by.bsuir.webproj.logic;

import by.bsuir.webproj.container.Commentary;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.dao.CommentDAO;
import by.bsuir.webproj.dao.FilmsDAO;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class AddCommentLogic {
    private final static Logger LOGGER = LogManager.getLogger(AddCommentLogic.class);

    public static boolean commentFilm(int pid, int fid, String desc, int rating) throws LogicException {
        boolean result = false;
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            CommentDAO dao = new CommentDAO(conn);
            Commentary comment = new Commentary();
            comment.setPid(pid);
            comment.setFid(fid);
            comment.setFilmComment(desc);
            comment.setRating(rating);
            if (dao.addEntity(comment)) {
                result = true;
                LOGGER.debug("Comment added");
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

    public static ArrayList<Film> findNotCommentedFilms(int id) throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            CommentDAO dao = new CommentDAO(conn);
            ArrayList<Film> films;
            if ((films = dao.findNonCommentedFilms(id)) != null) {
                LOGGER.debug("Films found");
                return films;
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
