package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.ConfigurationManager;

public class ÑhangeLanguageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ÑhangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {


        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        LOG.info("language " +language);
        LOG.info("Ñòðàíèöà îò êóäà ïðèøåë çàïðîñ " +request.getRequestURI());


        return getPageRole(session.getAttribute("userType").toString());
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
            propertiesName = "path.page.login";
            System.out.println("Eroor");
            break;
        }

        return ConfigurationManager.getProperty(propertiesName);

    }

}
