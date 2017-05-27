package ua.nure.gnuchykh.web.command.sort;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class SortCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SortCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));
        LOG.info("type "+ request.getParameter("typeSort")+"; object "+request.getParameter("object"));

        String typeSort =request.getParameter("typeSort");
        String object =request.getParameter("object");
        HttpSession session = request.getSession();

        if(object.equals("cars")) {
            List<Car> list = (List<Car>) session.getAttribute("cars");
            Collections.sort(list, SortFactory.getCarComparator(typeSort));
            session.setAttribute("cars", list);


        } else if(object.equals("users")) {
            List<User> list = (List<User>) session.getAttribute("users");
            Collections.sort(list, SortFactory.getUserComparator(typeSort));
            session.setAttribute("users", list);

        }

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
