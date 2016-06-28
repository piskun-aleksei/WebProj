package by.bsuir.webproj.command;

import by.bsuir.webproj.exception.*;
import by.bsuir.webproj.logic.RegistrationLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
    private static final String ERROR_REGISTER = "errorRegisterMessage";
    private final static String IS_ADMIN = "isAdmin";
    private final static String USER_ID = "userId";
    private final static String USER_PASSWORD = "userPassword";
    private final static String REG_LOGIN = "regLogin";
    private final static String REG_PASS = "regPass";
    private final static String REG_NAME = "regName";
    private final static String REG_SURNAME = "regSurname";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = (String) request.getSession().getAttribute(PAGE);
        try {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);

            request.setAttribute(PAGE, request.getAttribute(PAGE));
            request.setAttribute(REG_LOGIN, login);
            request.setAttribute(REG_NAME, name);
            request.setAttribute(REG_PASS, password);
            request.setAttribute(REG_SURNAME, surname);
            if (RegistrationLogic.registrate(login, password, name, surname)) {
                request.getSession().setAttribute(LOGIN, login);
                request.getSession().setAttribute(IS_ADMIN, RegistrationLogic.isAdmin());
                request.getSession().setAttribute(USER_ID, RegistrationLogic.getUserId());
                request.getSession().setAttribute(USER_PASSWORD, RegistrationLogic.getUserPassword());
                LOGGER.debug("User registered");
            } else {
                request.setAttribute(ERROR_REGISTER, "Login is used, try another one");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
