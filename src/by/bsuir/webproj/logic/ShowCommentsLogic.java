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

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class ShowCommentsLogic {
    private final static Logger LOGGER = LogManager.getLogger(ShowCommentsLogic.class);

    public static ArrayList<Commentary> searchForComments(int fid) throws LogicException {
        ConnectionPool pool = null;
        WrapperConnection conn = null;
        try {
            pool = ConnectionPool.getInstance();
            conn = pool.getConnection();
            CommentDAO dao = new CommentDAO(conn);
            ArrayList<Commentary> commentaries = dao.findCommentsByFid(fid);
            LOGGER.debug("Comments found");
            return commentaries;
        } catch (ConnectionPoolException e) {
            throw new LogicException("Pool exception", e);
        } catch (DAOException e) {
            throw new LogicException("UserDAO exception ", e);
        } finally {
            pool.closeConnection(conn);
        }
    }
}
