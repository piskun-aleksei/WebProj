package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.exception.UserLoginException;
import by.bsuir.webproj.logic.ShowFilmsLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Алексей on 24.04.2016.
 */
public class ShowFilmsCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ShowFilmsCommand.class);
    private static final String PAGE = "page";
    private static final String FILMS = "films";


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            request.getSession().setAttribute(PAGE, JSPPaths.FILMS);
            String result = JSPPaths.MAIN;
            ArrayList<Film> films;
            films = ShowFilmsLogic.searchForFilms();
            if (films != null) {
                request.getSession().setAttribute(FILMS, films);
                result = JSPPaths.FILMS;
                LOGGER.debug("Films found");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
