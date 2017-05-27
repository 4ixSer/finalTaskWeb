package ua.nure.gnuchykh.web.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class SortCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SortCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));
        LOG.info("type "+ request.getParameter("typeSort")+"; object "+request.getParameter("object"));

        HttpSession session = request.getSession();


        List<Car> list = (List<Car>) session.getAttribute("cars");
        Collections.sort(list, new Comparator<Car>() {
                @Override
                public int compare(Car o1, Car o2) {
                        return o1.getNamber().toString().compareTo(o2.getNamber().toString());
                }
        });

        session.setAttribute("cars", list);

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
