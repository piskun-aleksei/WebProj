package by.bsuir.webproj.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 10.04.2016.
 */
public class LocaleCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger(LocaleCommand.class);
    private final static String LOCALE = "locale";
    private final static String RU = "ru_RU";
    private final static String EN = "en_US";
    private final static String PAGE = "page";
    private final static String RUS_BUTTON = "localizeButtonMainRus";

    @Override
    public String execute(HttpServletRequest request) {
        String rus = request.getParameter(RUS_BUTTON);
        if (rus != null) {
            request.getSession().setAttribute(LOCALE, RU);
        } else {
            request.getSession().setAttribute(LOCALE, EN);
        }
        String result = (String) request.getSession().getAttribute(PAGE);
        LOGGER.debug("Locale changed");
        return result;

    }
}
