package ua.nure.gnuchykh.web.command.driver;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_ALL_FLIGHT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * The command to close the flight driver.
 *
 * @author qny4ix
 *
 */

public final class UpdateFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы ");
        HttpSession session = request.getSession();

        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        String idFlightS = request.getParameter(PARAM_NAME_ID);
        String statusCarS = request.getParameter(PARAM_NAME_CAR_STATUS);

        if (!Validation.parameterStringIsCorrect(statusCarS, idFlightS, comments)
                || !Validation.comentIsCorrect(comments)) {
            LOG.info(
                    "Ошибка валидации comments " + comments + "; idFlightS " + idFlightS + "; statusCarS" + statusCarS);
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
        } else {
            Integer idFlight = null;
            Integer statusCar = null;

            try {
                idFlight = Integer.parseInt(idFlightS);
                statusCar = Integer.parseInt(statusCarS);

            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга idFlight " + idFlightS + "; statusCar " + statusCar);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_DRIVER;
            }

            Flight flight = null;
            List<Flight> list = (List<Flight>) session.getAttribute(ATTRIBUTE_ALL_FLIGHT);
            for (Flight flight2 : list) {
                if (flight2.getNamberFlight() == idFlight) {
                    flight2.setStatus(ua.nure.gnuchykh.entity.subject.Status.CLOSED);
                    flight = flight2;
                }
            }

            FlightDAO flightDAO = new FlightDAO();
            flightDAO.update(flight, flight.getCar(), Status.fromValue(statusCar), comments);

            session.setAttribute(ATTRIBUTE_ALL_FLIGHT, list);
            session.setAttribute("Message", MessageManager.getProperty("message.flight.close.successful"));
            LOG.info("Успешное зекрытие рейса idFlight " + idFlight);

        }
        return Path.PAGE_DRIVER;
    }
}
