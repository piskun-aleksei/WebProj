package by.bsuir.webproj.dao;

import by.bsuir.webproj.container.Commentary;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.WrapperConnection;
import by.bsuir.webproj.util.CurrentDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class CommentDAO extends AbstractDAO<Commentary> {
    private final static Logger LOGGER = LogManager.getLogger(CommentDAO.class);

    private static final String SQL_SELECT_ALL_COMMENTS = "SELECT id, pid, fid, film_comment, rating, date, time FROM commentaries";
    private static final String SQL_SELECT_COMMENT_BY_FID = "SELECT id, pid, fid, film_comment, rating, date, time FROM commentaries WHERE fid=?";
    private static final String SQL_INSERT_COMMENT =
            "INSERT INTO commentaries(pid, fid, film_comment, rating, date, time) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_NON_COMMENTED_FILMS = "SELECT films.id, films.name, films.film_description, films.age_rating, " +
            "films.price FROM films INNER JOIN orders ON films.id = orders.fid AND films.id NOT IN \n" +
            "(SELECT fid FROM commentaries WHERE pid=?) AND orders.order_status=\"Complete\" AND orders.pid=?";
    private static final String SQL_SELECT_LOGIN_BY_PID = "SELECT login FROM users WHERE id=?";

    private static final String ID = "id";
    private static final String PID = "pid";
    private static final String FID = "fid";
    private static final String FILM_COMMENT = "film_comment";
    private static final String RATING = "rating";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String NAME = "name";
    private static final String FILM_DESC = "film_description";
    private static final String AGE_RATING = "age_rating";
    private static final String PRICE = "price";
    private static final String LOGIN = "login";

    public CommentDAO(WrapperConnection connection) {
        super(connection);
    }

    @Override
    public ArrayList<Commentary> findAll() throws DAOException {
        ArrayList<Commentary> commentaries = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_ALL_COMMENTS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Commentary commentary = new Commentary();
                commentary.setId(resultSet.getInt(ID));
                commentary.setFid(resultSet.getInt(FID));
                commentary.setPid(resultSet.getInt(PID));
                commentary.setFilmComment(resultSet.getString(FILM_COMMENT));
                commentary.setRating(resultSet.getInt(RATING));
                commentary.setDate(CurrentDate.getStringDate(resultSet.getDate(DATE)));
                commentary.setTime(CurrentDate.getStringTime(resultSet.getTime(TIME)));
                commentaries.add(commentary);
            }
            LOGGER.debug("Films found");
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        } catch (WrapperConnectionException e) {
            throw new DAOException("WrapperConnection exc", e);
        } finally {
            try {
                connection.closeStatement(st);
            } catch (WrapperConnectionException e) {
                LOGGER.error("Connection exception");
            }
        }
        return commentaries;
    }


    @Override
    public boolean addEntity(Commentary entity) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_INSERT_COMMENT);
            st.setInt(1, entity.getPid());
            st.setInt(2, entity.getFid());
            st.setString(3, entity.getFilmComment());
            st.setInt(4, entity.getRating());
            st.setDate(5, Date.valueOf(CurrentDate.getCurrentDate().trim()));
            st.setTime(6, Time.valueOf(CurrentDate.getCurrentTime().trim()));
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        } catch (WrapperConnectionException e) {
            throw new DAOException("WrapperConnection exc", e);
        } finally {
            try {
                connection.closeStatement(st);
            } catch (WrapperConnectionException e) {
                LOGGER.error("Connection exception");
            }
        }
        return result;
    }

    public ArrayList<Film> findNonCommentedFilms(int id) throws DAOException {
        ArrayList<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_NON_COMMENTED_FILMS);
            st.setInt(1, id);
            st.setInt(2, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setDesc(resultSet.getString(FILM_DESC));
                film.setName(resultSet.getString(NAME));
                film.setAgeRating(resultSet.getInt(AGE_RATING));
                film.setPrice(resultSet.getInt(PRICE));
                films.add(film);
            }
            LOGGER.debug("Films found");
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        } catch (WrapperConnectionException e) {
            throw new DAOException("WrapperConnection exc", e);
        } finally {
            try {
                connection.closeStatement(st);
            } catch (WrapperConnectionException e) {
                LOGGER.error("Connection exception");
            }
        }
        return films;
    }

    public ArrayList<Commentary> findCommentsByFid(int fid) throws DAOException {
        ArrayList<Commentary> commentaries = new ArrayList<>();
        PreparedStatement st = null;
        try {
            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_COMMENT_BY_FID);
            st.setInt(1, fid);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Commentary commentary = new Commentary();
                commentary.setId(resultSet.getInt(ID));
                commentary.setPid(resultSet.getInt(PID));
                commentary.setFilmComment(resultSet.getString(FILM_COMMENT));
                commentary.setFid(resultSet.getInt(FID));
                commentary.setRating(resultSet.getInt(RATING));
                commentary.setDate(CurrentDate.getStringDate(resultSet.getDate(DATE)));
                commentary.setTime(CurrentDate.getStringTime(resultSet.getTime(TIME)));
                commentaries.add(commentary);
            }
            for (int i = 0; i < commentaries.size(); i++) {
                st = connection.getStatement(SQL_SELECT_LOGIN_BY_PID);
                st.setInt(1, commentaries.get(i).getPid());
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    commentaries.get(i).setPersonName(resultSet.getString(LOGIN));
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.debug("Comments found");
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                throw new DAOException("SQL exception", e);
            }
            throw new DAOException("SQL exception", e);
        } catch (WrapperConnectionException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                throw new DAOException("SQL exception", e);
            }
            throw new DAOException("WrapperConnection exc", e);
        } finally {
            try {
                connection.closeStatement(st);
            } catch (WrapperConnectionException e) {
                LOGGER.error("Connection exception");
            }
        }
        return commentaries;
    }

}
