package ua.nure.gnuchykh.web.command.sort;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class SortCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SortCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();

        String typeSort = request.getParameter("typeSort");
        String object = request.getParameter("object");

        if (!Validation.parameterStringIsCorrect(typeSort, object)
                || !Validation.validateString(typeSort, object)) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны");

        } else {
            if (object.equals("cars")) {
                List<Car> list = (List<Car>) session.getAttribute("cars");
                if (list != null) {
                    Collections.sort(list, SortFactory.getCarComparator(typeSort));
                    session.setAttribute("cars", list);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else if (object.equals("users")) {
                List<User> list = (List<User>) session.getAttribute("users");
                // если вызываеться get
                if (list != null) {
                    Collections.sort(list, SortFactory.getUserComparator(typeSort));
                    session.setAttribute("users", list);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else if (object.equals("allRequest")) {
                List<Request> list = (List<Request>) session.getAttribute("allRequest");
                if (list != null) {
                    Collections.sort(list, SortFactory.getRequestComparator(typeSort));
                    session.setAttribute("allRequest", list);
                    System.err.println(2);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else if (object.equals("allFlight")) {
                List<Flight> list = (List<Flight>) session.getAttribute("allFlight");
                if (list != null) {
                    Collections.sort(list, SortFactory.getFlightComparator(typeSort));
                    session.setAttribute("allFlight", list);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else {
                session.setAttribute("Message", MessageManager.getProperty("message.sort.error"));
                LOG.info("Данные не коректны");
            }
        }

        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
