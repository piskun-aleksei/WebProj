package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddCreditLogic;
import by.bsuir.webproj.logic.AddUserBonusLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class AddCreditCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AddCreditCommand.class);
    private static final String PAGE = "page";
    private static final String USER_ID = "userId";
    private static final String CREDIT = "credit";
    private static final String MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            int userId = (int) request.getSession().getAttribute(USER_ID);
            int credit = Integer.parseInt(request.getParameter(CREDIT));
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (AddCreditLogic.addCredit(userId, credit)) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                result = JSPPaths.PROFILE;
                LOGGER.debug("Credit added");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to add a credit");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
