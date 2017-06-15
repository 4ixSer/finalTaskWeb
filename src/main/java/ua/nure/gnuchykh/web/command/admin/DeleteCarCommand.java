package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_CARS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * Command to remove the machine in the database.
 *
 * @author qny4ix
 *
 */
public final class DeleteCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы ");
        HttpSession session = request.getSession();
        String idS = request.getParameter(PARAM_NAME_ID);

        // Валилация
        if (!Validation.parameterStringIsCorrect(idS)) {
            LOG.info("Ошибка валидации idS " + idS);
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
        } else {

            Integer id = null;
            // Парсинг
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга id" + idS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_ADMIN;
            }

            //проверка используется эта машина или нет
            FlightDAO flightDAO = new FlightDAO();
            List<Flight> flights = flightDAO.findEntityByCarId(id);
            if (!flights.isEmpty()) {
                LOG.info("Нужно закрыть или удалить рейсы связанные с этой машиной id " + id);
                session.setAttribute("Message", MessageManager.getProperty("message.car.close.flights"));
            } else {

                CarDAO dao = new CarDAO();
                dao.delete(id);
                List<Car> cars = dao.findAll();
                session.setAttribute(ATTRIBUTE_CARS, cars);
                session.setAttribute("Message", MessageManager.getProperty("message.car.delete.successful"));
                LOG.debug("Successfully removed the car id " + id);
            }
        }
        return Path.PAGE_ADMIN;
    }
}
