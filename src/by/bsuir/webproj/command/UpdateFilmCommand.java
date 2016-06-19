package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.ShowFilmsLogic;
import by.bsuir.webproj.logic.UpdateFilmLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableInterceptor.ServerRequestInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class UpdateFilmCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(UpdateFilmCommand.class);
    private static final String PAGE = "page";
    private static final String NAME = "name";
    private static final String DESC = "desc";
    private static final String AGE_RATING = "ageRating";
    private static final String PRICE = "price";
    private static final String FILM_ID = "filmId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            request.getSession().setAttribute(PAGE, JSPPaths.FILMS);
            String result = JSPPaths.MAIN;
            Film film = new Film();
            String name = request.getParameter(NAME);
            String desc = request.getParameter(DESC);
            int ageRating = Integer.parseInt(request.getParameter(AGE_RATING));
            int price = Integer.parseInt(request.getParameter(PRICE));
            int id = Integer.parseInt(request.getParameter(FILM_ID));
            film.setName(name);
            film.setDesc(desc);
            film.setAgeRating(ageRating);
            film.setPrice(price);
            film.setId(id);
            if (UpdateFilmLogic.updateFilm(film)) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                result = JSPPaths.PROFILE;
                LOGGER.debug("Films found");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
