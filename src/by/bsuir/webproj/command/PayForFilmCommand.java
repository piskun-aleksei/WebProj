package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.PayForFilmLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Brotorias on 11.06.2016.
 */
public class PayForFilmCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(PayForFilmCommand.class);
    private static final String PAGE = "page";
    private static final String MESSAGE = "moneyErrorMessage";
    private static final String FILM_TO_PAY_ID = "filmToPayId";
    private static final String USER_ID = "userId";
    private static final String FILM = "film";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = request.getParameter(PAGE);
        int filmId = Integer.parseInt(request.getParameter(FILM_TO_PAY_ID));
        int userId = (int) request.getSession().getAttribute(USER_ID);
        int filmPrice = Integer.parseInt(request.getParameter(FILM.concat(new Integer(filmId).toString())));
        try {
            if (PayForFilmLogic.payForFilm(userId, filmId, filmPrice)) {
                LOGGER.debug("Film payed");
            } else {
                result = JSPPaths.FILM_PAYMENT;
                request.setAttribute(MESSAGE, "Error while trying to pay for a film (not enough money)");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
