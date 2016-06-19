package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;
import by.bsuir.webproj.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 04.05.2016.
 */
public class ProfileCommand implements ActionCommand {
    private final static String PAGE = "page";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(PAGE, JSPPaths.PROFILE);
        return JSPPaths.PROFILE;
    }
}
