package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddFilmLogic;
import by.bsuir.webproj.logic.ChangeLoginLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 04.05.2016.
 */
public class ChangeLoginCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ChangeLoginCommand.class);
    private static final String PAGE = "page";
    private static final String LOGIN = "login";
    private static final String NEW_LOGIN = "newLogin";
    private static final String MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            String login = (String) request.getSession().getAttribute(LOGIN);
            String newLogin = request.getParameter(NEW_LOGIN);
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (ChangeLoginLogic.changeLogin(login, newLogin)) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                request.getSession().setAttribute(LOGIN, newLogin);
                result = JSPPaths.PROFILE;
                LOGGER.debug("Login changed");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to change login");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
