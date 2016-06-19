package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.dao.UserDAO;
import by.bsuir.webproj.exception.*;
import by.bsuir.webproj.handler.CryptingHashHandler;
import by.bsuir.webproj.logic.LoginLogic;
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
public class LoginCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private final static String PAGE = "page";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String ERROR_LOGIN = "errorLoginPassMessage";
    private final static String IS_ADMIN = "isAdmin";
    private final static String USER_ID = "userId";
    private final static String USER_PASSWORD = "userPassword";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = (String) request.getSession().getAttribute(PAGE);

        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (LoginLogic.login(login, password) != null) {
                request.getSession().setAttribute(LOGIN, login);
                request.getSession().setAttribute(IS_ADMIN, LoginLogic.isAdmin());
                request.getSession().setAttribute(USER_ID, LoginLogic.getUserId());
                request.getSession().setAttribute(USER_PASSWORD, LoginLogic.getUserPassword());
            } else {
                request.setAttribute(ERROR_LOGIN, "Invalid login or password");
                LOGGER.debug("User not found (invalid login or pass)");
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return result;
    }

}
