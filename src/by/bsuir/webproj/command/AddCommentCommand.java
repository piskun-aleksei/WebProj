package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddCommentLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class AddCommentCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AddCommentCommand.class);
    private static final String PAGE = "page";
    private static final String USER_ID = "userId";
    private static final String DESC = "desc";
    private static final String RATING = "rating";
    private static final String MESSAGE = "errorMessage";
    private static final String FILM_ID = "filmId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            int pid = (int) request.getSession().getAttribute(USER_ID);
            String desc = request.getParameter(DESC);
            int fid = Integer.parseInt(request.getParameter(FILM_ID));
            int rating = Integer.parseInt(request.getParameter(RATING));
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (AddCommentLogic.commentFilm(pid, fid, desc, rating)) {
                request.getSession().setAttribute(PAGE, JSPPaths.FILMS);
                result = JSPPaths.FILMS;
                LOGGER.debug("Film commented");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to comment a film");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
