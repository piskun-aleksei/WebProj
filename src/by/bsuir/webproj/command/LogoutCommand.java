package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.LogicException;
import by.bsuir.webproj.logic.LoginLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 28.04.2016.
 */
public class LogoutCommand implements ActionCommand {
    private final static String PAGE = "page";
    private final static String LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String result = JSPPaths.MAIN;
        request.getSession().setAttribute(PAGE, JSPPaths.MAIN);
        request.getSession().setAttribute(LOGIN, null);
        return result;
    }
}
