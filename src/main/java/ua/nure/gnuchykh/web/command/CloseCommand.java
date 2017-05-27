package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.ConfigurationManager;

public class CloseCommand  implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CloseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Начало работы");

        // TODO валидация данных
        String table = request.getParameter("table");

        HttpSession session = request.getSession();

        session.removeAttribute(table);

        LOG.info("Закрытие таблицы " +table);


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
