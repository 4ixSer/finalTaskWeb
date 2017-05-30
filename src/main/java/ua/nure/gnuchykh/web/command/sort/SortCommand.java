package ua.nure.gnuchykh.web.command.sort;

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

        if (typeSort == null || typeSort.isEmpty() || object == null || object.isEmpty()
                || !Validation.validateString(typeSort, object)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("Данные не коректны");

        } else {
            // TODO подумать как красивей сделать
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
            } else if (object.equals("userRequest")) {
                List<Request> list = (List<Request>) session.getAttribute("userRequest");
                if (list != null) {
                    Collections.sort(list, SortFactory.getRequestComparator(typeSort));
                    session.setAttribute("users", list);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else if (object.equals("allFlight")) {
                @SuppressWarnings("unchecked")
                List<Flight> list = (List<Flight>) session.getAttribute("allFlight");
                Collections.sort(list, SortFactory.getFlightComparator(typeSort));
                if (list != null) {
                    session.setAttribute("allFlight", list);
                    session.setAttribute("Message", MessageManager.getProperty("message.sort") + " " + typeSort);
                }
            } else {
                session.setAttribute("Message", MessageManager.getProperty("message.sortError"));
                LOG.info("Данные не коректны");
            }
        }

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
