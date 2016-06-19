package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Commentary;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.ShowCommentsLogic;
import by.bsuir.webproj.logic.ShowFilmsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class ShowCommentsCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ShowCommentsCommand.class);
    private static final String PAGE = "page";
    private static final String COMMENTS = "comments";
    private static final String FILM_ID = "filmId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            request.getSession().setAttribute(PAGE, JSPPaths.COMMENTS);
            int fid = Integer.parseInt(request.getParameter(FILM_ID));
            String result = JSPPaths.FILMS;
            ArrayList<Commentary> commentaries;
            commentaries = ShowCommentsLogic.searchForComments(fid);
            if (commentaries != null) {
                if (commentaries.size() == 0) {
                    commentaries = null;
                }
                request.getSession().setAttribute(COMMENTS, commentaries);
                result = JSPPaths.COMMENTS;
                LOGGER.debug("Comments found");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
