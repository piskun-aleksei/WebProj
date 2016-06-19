package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.handler.CryptingHashHandler;
import by.bsuir.webproj.logic.ChangeLoginLogic;
import by.bsuir.webproj.logic.ChangePasswordLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class ChangePasswordCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);
    private static final String PAGE = "page";
    private static final String LOGIN = "login";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String MESSAGE = "errorPassMessage";
    private static final String USER_PASSWORD = "userPassword";
    private static final String OLD_PASSWORD = "oldPassword";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            String login = (String) request.getSession().getAttribute(LOGIN);
            String password = (String) request.getSession().getAttribute(USER_PASSWORD);
            String oldPassword = request.getParameter(OLD_PASSWORD);
            String newPassword = request.getParameter(NEW_PASSWORD);
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (!password.equals(CryptingHashHandler.getHash(oldPassword))) {
                request.setAttribute(MESSAGE, "Error while trying to change pass (password is incorrect)");
                result = JSPPaths.CHANGE_PASSWORD;
                return result;
            }
            if (ChangePasswordLogic.changePassword(login, CryptingHashHandler.getHash(newPassword))) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                request.getSession().setAttribute(USER_PASSWORD, CryptingHashHandler.getHash(newPassword));
                result = JSPPaths.PROFILE;
                LOGGER.debug("Login changed");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to change password");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
