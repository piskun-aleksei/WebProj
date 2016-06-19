package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.DeleteUserLogic;
import javafx.scene.text.FontWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class FindUsersPreparationCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(FindUsersPreparationCommand.class);
    private static final String PAGE = "page";
    private static final String NON_ADMINS = "nonAdmins";
    private static final String MESSAGE = "message";
    private static final String FORW_PAGE = "forwPage";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        String forw = request.getParameter(FORW_PAGE);
        try {
            ArrayList<User> users;
            if ((users = DeleteUserLogic.findNonAdmins()) != null) {
                request.getSession().setAttribute(NON_ADMINS, users);
                result = forw;
                LOGGER.debug("Users found");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to find user");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
