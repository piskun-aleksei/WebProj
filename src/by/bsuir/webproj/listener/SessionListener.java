package by.bsuir.webproj.listener;

import by.bsuir.webproj.command.RegistrationCommand;
import by.bsuir.webproj.constant.JSPPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private final static Logger LOGGER = LogManager.getLogger(SessionListener.class);
    private static final String PAGE = "page";
    private static final String LOCALE = "locale";
    private static final String EN = "en_US";
    private static final String IS_ADMIN = "isAdmin";
    private static final String LOGIN = "login";
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(LOCALE, EN);
        session.setAttribute(PAGE, JSPPaths.MAIN);
        session.setAttribute(IS_ADMIN, false);
        session.setAttribute(LOGIN, null);
        LOGGER.debug("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.debug("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}