package by.bsuir.webproj.command;

import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.User;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.DeleteUserLogic;
import by.bsuir.webproj.logic.UpdateFilmLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class FindFilmsCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(FindFilmsCommand.class);
    private static final String PAGE = "page";
    private static final String FILMS = "films";
    private static final String MESSAGE = "message";
    private static final String FORW_PAGE = "forwPage";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        String forw = request.getParameter(FORW_PAGE);
        try {
            ArrayList<Film> films;
            if ((films = UpdateFilmLogic.findAllFilms()) != null) {
                request.getSession().setAttribute(FILMS, films);
                result = forw;
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
