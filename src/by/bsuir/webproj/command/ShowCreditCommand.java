package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Commentary;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.ShowCommentsLogic;
import by.bsuir.webproj.logic.ShowCreditLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class ShowCreditCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ShowCreditCommand.class);
    private static final String PAGE = "page";
    private static final String USER_ID = "userId";
    private static final String CREDIT = "credit";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
            int id = (int) request.getSession().getAttribute(USER_ID);
            String result = JSPPaths.FILMS;
            String credit;
            credit = ShowCreditLogic.showCredit(id);
            if (credit != null) {
                request.getSession().setAttribute(PAGE, JSPPaths.ACCOUNT_CREDIT);
                request.getSession().setAttribute(CREDIT, credit);
                result = JSPPaths.ACCOUNT_CREDIT;
                LOGGER.debug("Credit found");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
