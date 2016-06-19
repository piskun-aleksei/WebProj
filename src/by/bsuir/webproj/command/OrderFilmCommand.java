package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.OrderFilmLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 10.06.2016.
 */
public class OrderFilmCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(OrderFilmCommand.class);
    private static final String PAGE = "page";
    private static final String MESSAGE = "message";
    private static final String FILMS_ORDER = "filmsOrder";
    private static final String FILM_NAME = "filmName";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        String filmName = request.getParameter(FILM_NAME);
        int userId = (int) request.getSession().getAttribute(USER_ID);
        Film film = null;
        ArrayList<Film> films = (ArrayList<Film>) request.getSession().getAttribute(FILMS_ORDER);
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getName().equals(filmName)) {
                film = films.get(i);
            }
        }
        try {
            if (OrderFilmLogic.orderFilm(film, userId)) {
                LOGGER.debug("Film ordered");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to order a film");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
