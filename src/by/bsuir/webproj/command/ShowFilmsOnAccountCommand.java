package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.container.Film;
import by.bsuir.webproj.container.FilmOnAccount;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.ShowFilmsLogic;
import by.bsuir.webproj.logic.ShowFilmsOnAccountLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Brotorias on 12.06.2016.
 */
public class ShowFilmsOnAccountCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(ShowFilmsOnAccountCommand.class);
    private static final String PAGE = "page";
    private static final String FILMS = "filmsOnAccount";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            int id = (int) request.getSession().getAttribute(USER_ID);
            String result = JSPPaths.MAIN;
            ArrayList<FilmOnAccount> films;
            films = ShowFilmsOnAccountLogic.searchForFilmsOnAccount(id);
            if (films != null) {
                if(films.size() == 0){
                    films = null;
                }
                request.getSession().setAttribute(PAGE, JSPPaths.FILMS_ON_ACCOUNT);
                request.getSession().setAttribute(FILMS, films);
                result = JSPPaths.FILMS_ON_ACCOUNT;
                LOGGER.debug("Films found");
            }
            return result;
        } catch (LogicException e) {
            throw new CommandException("Logic exception ", e);
        }
    }
}
