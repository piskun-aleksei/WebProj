package by.bsuir.webproj.factory;

import by.bsuir.webproj.command.ActionCommand;
import by.bsuir.webproj.command.EmptyCommand;
import by.bsuir.webproj.enumerator.CommandEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 10.04.2016.
 */
public class CommandFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase()); /** Check for a command to exist in a system **/
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
        }
        return current;
    }
}
