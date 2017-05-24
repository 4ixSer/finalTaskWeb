package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.util.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        /*
         * в случае ошибки или прямого обращения к контроллеру переадресация на
         * страницу ввода логина
         */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}