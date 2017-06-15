package ua.nure.gnuchykh.web.command.administrator;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_ALL_FLIGHT;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * Command to remove a flight.
 *
 * @author qny4ix
 *
 */
public final class DeleteFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы");
        String idS = request.getParameter(PARAM_NAME_ID);
        HttpSession session = request.getSession();

        //валидация данных
        if (!Validation.parameterStringIsCorrect(idS)) {
            LOG.info("Ошибка валидации: idS " + idS);
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
        } else {
            Integer id = null;
            //парсинг
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга: id " + idS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
            }

            FlightDAO dao = new FlightDAO();
            Flight flight = dao.findEntityById(id);

            //проверка статуса рейса
            if (flight!=null && flight.getStatus() == Status.CLOSED) {
                dao.delete(id);
                List<Flight> list = dao.findAll();
                session.setAttribute(ATTRIBUTE_ALL_FLIGHT, list);
                session.setAttribute("Message", MessageManager.getProperty("message.flight.delete.successful"));
                LOG.info("Рейс успешно удалён id " +  id);
            } else {
                session.setAttribute("Message", MessageManager.getProperty("message.flight.delete.ex"));
                LOG.info("Рейс нельзя удалить id " + id);
            }
        }
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
