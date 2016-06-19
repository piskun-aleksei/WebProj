package by.bsuir.webproj.dao;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.FilmOnAccount;
import by.bsuir.webproj.exception.DAOException;
import by.bsuir.webproj.exception.WrapperConnectionException;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Алексей on 24.04.2016.
 */
public class FilmsDAO extends AbstractDAO<Film> {
    private final static Logger LOGGER = LogManager.getLogger(FilmsDAO.class);
    private static final String SQL_SELECT_ALL_FILMS = "SELECT id, name, film_description, age_rating, price FROM films";
    private static final String SQL_SELECT_FILM_BY_ID = "SELECT id, name, film_description, age_rating, price FROM films WHERE id=?";
    private static final String SQL_INSERT_FILM =
            "INSERT INTO films(name, film_description, age_rating, price) VALUES(?,?,?,?)";
    private static final String SQL_INSERT_FILM_ORDER =
            "INSERT INTO orders(pid, fid, order_status, price) VALUES(?,?,?,?)";
    private static final String SQL_SELECT_NON_ORDERED_FILMS = "SELECT id, name, film_description, age_rating, price FROM films WHERE id NOT IN (SELECT fid FROM orders WHERE pid=?)";
    private static final String SQL_SELECT_NON_PAYED_FILMS = "SELECT id, name, film_description, age_rating, price FROM films WHERE id IN (SELECT fid FROM orders WHERE pid=? AND order_status=\"Pending\") ";
    private static final String SQL_SELECT_CREDIT = "SELECT credit FROM users WHERE id=? ";
    private static final String SQL_SELECT_BONUS = "SELECT amount FROM bonuses WHERE pid=? ";
    private static final String SQL_UPDATE_BONUS = "UPDATE bonuses SET amount=? WHERE pid=?";
    private static final String SQL_UPDATE_CREDIT = "UPDATE users SET credit=? WHERE id=?";
    private static final String SQL_UPDATE_FIRM_CASH = "UPDATE film_company_data SET cash_amount=? WHERE cash_amount>=0";
    private static final String SQL_SELECT_FIRM_CASH = "SELECT cash_amount FROM film_company_data";
    private static final String SQL_UPDATE_STATUS = "UPDATE orders SET order_status=? WHERE pid=? and fid=?";
    private static final String SQL_SELECT_FILMS_ON_ACCOUNT = "SELECT fid, order_status FROM orders WHERE pid=?";
    private static final String SQL_SELECT_FILM_NAME = "SELECT name FROM films WHERE id=?";
    private static final String SQL_SELECT_COMMENT = "SELECT film_comment, rating FROM commentaries WHERE pid=? AND fid=?";
    private static final String SQL_UPDATE_FILM_INFO = "UPDATE films SET name=?, film_description=?, age_rating=?, price=? WHERE id=?";

    private static final String ID = "id";
    private static final String FID = "fid";
    private static final String NAME = "name";
    private static final String FILM_DESC = "film_description";
    private static final String AGE_RATING = "age_rating";
    private static final String PRICE = "price";
    private static final String CREDIT = "credit";
    private static final String BONUS = "amount";
    private static final String CASH_AMOUNT = "cash_amount";
    private static final String STATUS = "order_status";
    private static final String FILM_COMMENT = "film_comment";
    private static final String RATING = "rating";


    public FilmsDAO(WrapperConnection connection) {
        super(connection);
    }

    @Override
    public ArrayList<Film> findAll() throws DAOException {
        ArrayList<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_ALL_FILMS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setDesc(resultSet.getString(FILM_DESC));
                film.setAgeRating(resultSet.getInt(AGE_RATING));
                film.setName(resultSet.getString(NAME));
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

    public Film findEntityById(int id) throws DAOException {
        Film film = null;
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_FILM_BY_ID);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setName(resultSet.getString(NAME));
                film.setDesc(resultSet.getString(FILM_DESC));
                film.setAgeRating(resultSet.getInt(AGE_RATING));
                film.setPrice(resultSet.getInt(PRICE));
                LOGGER.debug(film.getName() + " found");
            }
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
        return film;
    }

    public boolean updateEntity(Film entity) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_UPDATE_FILM_INFO);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDesc());
            st.setInt(3, entity.getAgeRating());
            st.setInt(4, entity.getPrice());
            st.setInt(5, entity.getId());
            int results = st.executeUpdate();
            System.out.print("UPDATED " + results + entity.getId());
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

    @Override
    public boolean addEntity(Film entity) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_INSERT_FILM);
            st.setString(1, entity.getName());
            st.setString(2, entity.getDesc());
            st.setInt(3, entity.getAgeRating());
            st.setInt(4, entity.getPrice());
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

    public ArrayList<Film> findNonOrderedFilms(int id) throws DAOException {
        ArrayList<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {

            st = connection.getStatement(SQL_SELECT_NON_ORDERED_FILMS);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setAgeRating(resultSet.getInt(AGE_RATING));
                film.setName(resultSet.getString(NAME));
                film.setDesc(resultSet.getString(FILM_DESC));
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

    public boolean orderFilm(Film entity, int id) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_INSERT_FILM_ORDER);
            st.setInt(1, id);
            st.setInt(2, entity.getId());
            st.setString(3, "Pending");
            st.setInt(4, entity.getPrice());
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


    public boolean payForFilm(int pid, int fid, int filmPrice) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            int userCredit = 0;
            int userBonus = 0;
            int tempFilmPrice = filmPrice;
            int firmCash = 0;
            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_CREDIT);
            st.setInt(1, pid);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                userCredit = resultSet.getInt(CREDIT);
            }
            if (userCredit < filmPrice) {
                st = connection.getStatement(SQL_SELECT_BONUS);
                st.setInt(1, pid);
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    userBonus = resultSet.getInt(BONUS);
                }
                if ((userBonus + userCredit) < filmPrice) {
                    result = false;
                    connection.setAutoCommit(true);
                    return result;
                } else {
                    if (userBonus > filmPrice) {
                        userBonus -= filmPrice;
                        st = connection.getStatement(SQL_UPDATE_BONUS);
                        st.setInt(1, userBonus);
                        st.setInt(2, pid);
                        st.executeUpdate();
                    } else {
                        filmPrice -= userBonus;
                        st = connection.getStatement(SQL_UPDATE_BONUS);
                        st.setInt(1, 0);
                        st.setInt(2, pid);
                        st.executeUpdate();
                        userCredit -= filmPrice;
                        st = connection.getStatement(SQL_UPDATE_CREDIT);
                        st.setInt(1, userCredit);
                        st.setInt(2, pid);
                        st.executeUpdate();
                    }

                }
                st = connection.getStatement(SQL_SELECT_FIRM_CASH);
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    firmCash = resultSet.getInt(CASH_AMOUNT);
                }
                st = connection.getStatement(SQL_UPDATE_FIRM_CASH);
                st.setInt(1, firmCash + tempFilmPrice);
                st.executeUpdate();
                st = connection.getStatement(SQL_UPDATE_STATUS);
                st.setInt(2, pid);
                st.setInt(3, fid);
                st.setString(1, "Complete");
                st.executeUpdate();

            } else {
                userCredit -= filmPrice;
                st = connection.getStatement(SQL_UPDATE_CREDIT);
                st.setInt(1, userCredit);
                st.setInt(2, pid);
                st.executeUpdate();
                st = connection.getStatement(SQL_SELECT_FIRM_CASH);
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    firmCash = resultSet.getInt(CASH_AMOUNT);
                }
                st = connection.getStatement(SQL_UPDATE_FIRM_CASH);
                st.setInt(1, firmCash + tempFilmPrice);
                st.executeUpdate();
                st = connection.getStatement(SQL_UPDATE_STATUS);
                st.setInt(2, pid);
                st.setInt(3, fid);
                st.setString(1, "Complete");
                st.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
            result = true;
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
        return result;
    }

    public ArrayList<Film> findNonPayedFilms(int id) throws DAOException {
        ArrayList<Film> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_NON_PAYED_FILMS);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt(ID));
                film.setName(resultSet.getString(NAME));
                film.setDesc(resultSet.getString(FILM_DESC));
                film.setPrice(resultSet.getInt(PRICE));
                film.setAgeRating(resultSet.getInt(AGE_RATING));
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

    public ArrayList<FilmOnAccount> findFilmsOnAccount(int id) throws DAOException {
        ArrayList<FilmOnAccount> films = new ArrayList<>();
        PreparedStatement st = null;
        try {
            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_FILMS_ON_ACCOUNT);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                FilmOnAccount film = new FilmOnAccount();
                film.setId(resultSet.getInt(FID));
                film.setStatus(resultSet.getString(STATUS));
                films.add(film);
            }
            for (int i = 0; i < films.size(); i++) {
                st = connection.getStatement(SQL_SELECT_FILM_NAME);

                st.setInt(1, films.get(i).getId());
                resultSet = st.executeQuery();

                while (resultSet.next()) {
                    films.get(i).setName(resultSet.getString(NAME));
                }
                if (films.get(i).getStatus().equals("Complete")) {

                    st = connection.getStatement(SQL_SELECT_COMMENT);
                    st.setInt(1, id);
                    st.setInt(2, films.get(i).getId());
                    resultSet = st.executeQuery();

                    while (resultSet.next()) {
                        films.get(i).setComment(resultSet.getString(FILM_COMMENT));
                        films.get(i).setRating(resultSet.getInt(RATING));
                    }
                } else {

                    films.get(i).setComment(null);
                    films.get(i).setRating(null);
                }

            }
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.debug("Films found");
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
        return films;
    }

}
