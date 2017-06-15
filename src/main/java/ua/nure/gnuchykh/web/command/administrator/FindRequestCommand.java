package ua.nure.gnuchykh.web.command.administrator;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_REQUESTS_CAR;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 *
 *
 * @author qny4ix
 *
 */
public final class FindRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы");
        HttpSession session = request.getSession();

        RequestDAO dao = new RequestDAO();
        Request requestUser = dao.findFirstRequest(LocalDateTime.now());
        if (requestUser == null) {
            LOG.info("Заявок больше нет.");
            session.setAttribute("Message", MessageManager.getProperty("message.request.null"));
        } else {
            LOG.info("request " + requestUser);
            session.setAttribute(ATTRIBUTE_USER_REQUEST, requestUser);

            CarDAO daoDAO = new CarDAO();

            List<Car> list = daoDAO.findCarByСharacteristics(requestUser.getType(), Status.FREE,
                    requestUser.getCarryingCar(), requestUser.getAmountCar(), requestUser.getEnginePower());
            if (list.isEmpty()) {
                session.setAttribute("Message", MessageManager.getProperty("message.car.null"));
                LOG.info("Подходящих автомобилей больше нет.");
            } else {
                session.setAttribute(ATTRIBUTE_REQUESTS_CAR, list);
                session.setAttribute("Message", MessageManager.getProperty("message.request.find"));
                LOG.info("Получена новая заявка list " + list);
            }
        }
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
