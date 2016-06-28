package by.bsuir.webproj.dao;

import by.bsuir.webproj.container.User;
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
 * Created by Алексей on 12.04.2016.
 */
public class UserDAO extends AbstractDAO<User> {
    private final static Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, password, credit, access_level, is_admin, name, surname FROM users";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, login, password, credit, access_level, is_admin, name, surname FROM users WHERE login=?";
    private static final String SQL_SELECT_USER_NON_ADMIN = "SELECT id, login, password, credit, name, surname FROM users WHERE is_admin=false";
    private static final String SQL_INSERT_USER =
            "INSERT INTO users(login, password, credit, access_level, is_admin, name, surname) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_DELETE_USER =
            "DELETE FROM users WHERE login=?";
    private static final String SQL_UPDATE_LOGIN =
            "UPDATE users SET login=? WHERE login=?";
    private static final String SQL_UPDATE_PASSWORD =
            "UPDATE users SET password=? WHERE login=?";
    private static final String SQL_SELECT_BONUS =
            "SELECT amount FROM bonuses WHERE pid=?";
    private static final String SQL_UPDATE_BONUS =
            "UPDATE bonuses SET amount=? WHERE pid=?";
    private static final String SQL_INSERT_BONUS =
            "INSERT INTO bonuses(pid, amount) VALUES(?,?)";
    private static final String SQL_SELECT_CREDIT =
            "SELECT credit FROM users WHERE id=?";
    private static final String SQL_UPDATE_CREDIT =
            "UPDATE users SET credit=? WHERE id=?";


    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CREDIT = "credit";
    private static final String ACCESS_LEVEL = "access_level";
    private static final String IS_ADMIN = "is_admin";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String AMOUNT = "amount";

    public UserDAO(WrapperConnection connection) {
        super(connection);
    }

    public ArrayList<User> findAll() throws DAOException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setCredit(resultSet.getInt(CREDIT));
                user.setAccessLevel(resultSet.getInt(ACCESS_LEVEL));
                user.setIsAdmin(resultSet.getBoolean(IS_ADMIN));
                user.setName(resultSet.getString(NAME));
                user.setSurname(resultSet.getString(SURNAME));
                users.add(user);
            }
            LOGGER.debug("Users found");
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
        return users;
    }

    public ArrayList<User> findNonAdminEntities() throws DAOException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_USER_NON_ADMIN);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setCredit(resultSet.getInt(CREDIT));
                user.setName(resultSet.getString(NAME));
                user.setSurname(resultSet.getString(SURNAME));
                users.add(user);
            }
            LOGGER.debug("Users found");
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
        return users;
    }

    public User findEntityByLogin(String login) throws DAOException {
        User user = null;
        PreparedStatement st = null;
        try {
            st = connection.getStatement(SQL_SELECT_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setCredit(resultSet.getInt(CREDIT));
                user.setAccessLevel(resultSet.getInt(ACCESS_LEVEL));
                user.setIsAdmin(resultSet.getBoolean(IS_ADMIN));
                user.setName(resultSet.getString(NAME));
                user.setSurname(resultSet.getString(SURNAME));
                LOGGER.debug(user.getLogin() + " found");
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
        return user;
    }

    @Override
    public boolean addEntity(User entity) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                if(resultSet.getString(LOGIN).equals(entity.getLogin())){
                    result = false;
                    return result;
                }
            }

            st = connection.getStatement(SQL_INSERT_USER);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setInt(3, entity.getCredit());
            st.setInt(4, entity.getAccessLevel());
            st.setBoolean(5, entity.getIsAdmin());
            st.setString(6, entity.getName());
            st.setString(7, entity.getSurname());
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

    public boolean deleteEntity(String login) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_DELETE_USER);
            st.setString(1, login);
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

    public boolean updateEntity(String login, String newLogin) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_UPDATE_LOGIN);
            st.setString(1, newLogin);
            st.setString(2, login);
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

    public boolean updateEntitysPass(String login, String newPassword) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            st = connection.getStatement(SQL_UPDATE_PASSWORD);
            System.out.print(newPassword + " " + login);
            st.setString(1, newPassword);
            st.setString(2, login);
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

    public boolean addBonus(int id, int bonus) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            int fullBonus = 0;
            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_BONUS);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                fullBonus = resultSet.getInt(AMOUNT);
                fullBonus += bonus;
                LOGGER.debug("Bonus found");
                st = connection.getStatement(SQL_UPDATE_BONUS);
                st.setInt(1, fullBonus);
                st.setInt(2, id);
                st.executeUpdate();
            } else {
                fullBonus = bonus;
                st = connection.getStatement(SQL_INSERT_BONUS);
                st.setInt(1, id);
                st.setInt(2, fullBonus);
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

    public boolean addCredit(int id, int credit) throws DAOException {
        PreparedStatement st = null;
        boolean result = false;
        try {
            int fullCredit = 0;
            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_CREDIT);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                fullCredit = resultSet.getInt(CREDIT);
                fullCredit += credit;
                LOGGER.debug("Credit found");
                st = connection.getStatement(SQL_UPDATE_CREDIT);
                st.setInt(1, fullCredit);
                st.setInt(2, id);
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

    public String showCredit(int id) throws DAOException {
        PreparedStatement st = null;
        Integer credit = 0;
        Integer bonus = 0;
        try {

            connection.setAutoCommit(false);
            st = connection.getStatement(SQL_SELECT_CREDIT);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                credit = resultSet.getInt(CREDIT);
                LOGGER.debug("Credit found");

            }
            st = connection.getStatement(SQL_SELECT_BONUS);
            st.setInt(1, id);
            resultSet = st.executeQuery();
            if (resultSet.next()) {
                bonus = resultSet.getInt(AMOUNT);
                LOGGER.debug("Bonus found");

            } else {
                bonus = 0;
            }
            connection.commit();
            connection.setAutoCommit(true);
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
        return "My credit: " + credit.toString() + " | My bonus: " + bonus.toString();
    }


}
