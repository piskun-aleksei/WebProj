package by.bsuir.webproj.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 10.04.2016.
 */
public class BackCommand implements ActionCommand {
    private final static String PAGE = "page";
    private final static String BACK_PAGE = "backPage";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(PAGE, request.getParameter(BACK_PAGE));
        return request.getParameter(BACK_PAGE);
    }
}

