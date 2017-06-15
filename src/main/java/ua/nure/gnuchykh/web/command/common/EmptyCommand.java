package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * The command in case of an error or direct access to the controller is redirected to the login page.
 *
 * @author qny4ix
 *
 */
public final class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        /*
         * в случае ошибки или прямого обращения к контроллеру переадресация на
         * страницу ввода логина
         */
        return Path.PAGE_LOGIN;
    }
}