package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.AddFilmLogic;
import by.bsuir.webproj.logic.RegistrationLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 04.05.2016.
 */
public class AddFilmCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(AddFilmCommand.class);
    private static final String PAGE = "page";
    private static final String NAME = "name";
    private static final String DESC = "desc";
    private static final String AGE_RATE = "ageRating";
    private static final String MESSAGE = "errorMessage";
    private static final String PRICE = "price";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result;
        try {
            String name = request.getParameter(NAME);
            String desc = request.getParameter(DESC);
            int ageRating = Integer.parseInt(request.getParameter(AGE_RATE));
            int price = Integer.parseInt(request.getParameter(PRICE));
            request.setAttribute(PAGE, request.getAttribute(PAGE));
            if (AddFilmLogic.addFilm(name, desc, ageRating, price)) {
                request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
                result = JSPPaths.PROFILE;
                LOGGER.debug("Film added");
            } else {
                request.setAttribute(MESSAGE, "Error while trying to add a film");
                result = (String) request.getSession().getAttribute(PAGE);
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
