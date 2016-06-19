package by.bsuir.webproj.command;

import by.bsuir.webproj.constant.JSPPaths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 24.04.2016.
 */
public class OpenCommand implements ActionCommand {
    private final static String FORW_PAGE = "forwPage";
    private final static String PAGE = "page";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(PAGE, request.getParameter(FORW_PAGE));
        return request.getParameter(FORW_PAGE);
    }
}