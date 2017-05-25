package ua.nure.gnuchykh.web.command;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.ConfigurationManager;

public class AddRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {


        HttpSession session = request.getSession();

        LOG.info("date " +request.getParameter("date"));

        LocalDateTime localDateRequest =LocalDateTime.parse(request.getParameter("date"));
        LocalDateTime localDate =LocalDateTime.now();

        LOG.info("date now" +localDate);
        LOG.info("date parse" +localDateRequest);


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
