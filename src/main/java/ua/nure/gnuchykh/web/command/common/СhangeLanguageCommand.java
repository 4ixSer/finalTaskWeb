package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class СhangeLanguageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(СhangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        LOG.info("language " + language);
        LOG.info("Страница от куда пришел запрос " + request.getRequestURI());
        //TODO передать красивый вывод
        if (session.getAttribute("userType") == null) {
            return ConfigurationManager.getProperty("path.page.login");
        } else {
            return getPageRole(session.getAttribute("userType").toString());
        }
    }

    private String getPageRole(String role) {

        String propertiesName = null;

        switch (role) {
        case "ADMINISTRATOR":
            propertiesName = "path.page.admin";
            break;
        case "DISPATCHER":
            propertiesName = "path.page.dispatcher";
            break;
        case "DRIVER":
            propertiesName = "path.page.driver";
            break;
        default:

            break;
        }

        return ConfigurationManager.getProperty(propertiesName);

    }

}
