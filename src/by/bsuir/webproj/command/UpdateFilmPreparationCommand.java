package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.PayForFilmLogic;
import by.bsuir.webproj.logic.UpdateFilmLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class UpdateFilmPreparationCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(UpdateFilmPreparationCommand.class);
    private static final String PAGE = "page";
    private static final String MESSAGE = "message";
    private static final String FILM = "film";
    private static final String FILM_ID = "filmId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        int filmId = Integer.parseInt(request.getParameter(FILM_ID));
        try {
            Film film;
            if ((film = UpdateFilmLogic.findFilm(filmId)) != null) {
                request.getSession().setAttribute(FILM, film);
                result = JSPPaths.UPDATE_FILM;
                LOGGER.debug("Film found");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to find film");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
