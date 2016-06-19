package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddUserBonusLogic;
import by.bsuir.webproj.logic.ChangeLoginLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class AddUserBonusCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AddUserBonusCommand.class);
    private static final String PAGE = "page";
    private static final String USER_ID = "userId";
    private static final String BONUS = "bonus";

    private static final String MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            int bonus = Integer.parseInt(request.getParameter(BONUS));
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (AddUserBonusLogic.addBonus(userId, bonus)) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                result = JSPPaths.PROFILE;
                LOGGER.debug("Bonus added");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to add a bonus");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
