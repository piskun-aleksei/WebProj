package by.bsuir.webproj.servlet;
import by.bsuir.webproj.command.ActionCommand;
import by.bsuir.webproj.exception.CommandException;
import by.bsuir.webproj.exception.ConnectionPoolException;
import by.bsuir.webproj.factory.CommandFactory;
import by.bsuir.webproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;
        CommandFactory client = new CommandFactory();

        ActionCommand command = client.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            throw new ServletException("Command exception ", e);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("nullPage",
                 page);
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy(){

            ConnectionPool.getInstance().closePool();

    }


}