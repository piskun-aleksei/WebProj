package by.bsuir.webproj.command;

import by.bsuir.webproj.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Алексей on 10.04.2016.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
