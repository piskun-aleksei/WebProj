package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.*;
import by.bsuir.webproj.handler.CryptingHashHandler;
import by.bsuir.webproj.logic.RegistrationLogic;
import by.bsuir.webproj.pool.ConnectionPool;
import by.bsuir.webproj.pool.WrapperConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Алексей on 10.04.2016.
 */
public class RegistrationCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private static final String PAGE = "page";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String MESSAGE = "message";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = (String) request.getSession().getAttribute(PAGE);
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (RegistrationLogic.registrate(login, password, name, surname)) {
                request.getSession().setAttribute(LOGIN, login);
                result = (String) request.getSession().getAttribute(PAGE);
                LOGGER.debug("User registered");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to register new user");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
