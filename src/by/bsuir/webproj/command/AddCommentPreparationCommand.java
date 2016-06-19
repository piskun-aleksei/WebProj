package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddCommentLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class AddCommentPreparationCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AddCommentPreparationCommand.class);
    private static final String PAGE = "page";
    private static final String MESSAGE = "message";
    private static final String FILMS_COMMENT = "filmsComment";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        int userId = (int) request.getSession().getAttribute(USER_ID);
        try {
            ArrayList<Film> films;
            if ((films = AddCommentLogic.findNotCommentedFilms(userId)) != null) {
                if (films.size() == 0) {
                    films = null;
                }
                request.getSession().setAttribute(FILMS_COMMENT, films);
                result = JSPPaths.ADD_COMMENT;
                LOGGER.debug("Films found");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to find films");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
