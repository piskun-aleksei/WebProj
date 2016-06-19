package by.bsuir.webproj.command;

import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.DeleteUserLogic;
import by.bsuir.webproj.logic.RegistrationLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class DeleteUserCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private static final String PAGE = "page";
    private static final String DELETE_LOGIN = "userToDelete";
    private static final String MESSAGE = "message";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        try {
            String login = request.getParameter(DELETE_LOGIN);
            if (DeleteUserLogic.deleteUser(login)) {
                LOGGER.debug("User deleted");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to delete user");
            }
            System.out.print(result);
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
