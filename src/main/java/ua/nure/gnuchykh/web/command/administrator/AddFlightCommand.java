package ua.nure.gnuchykh.web.command.administrator;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_REQUESTS_CAR;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_SELECTED_CAR;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;
/**
 * The command to add a flight.
 *
 * @author qny4ix
 *
 */

public final class AddFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddFlightCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("������ ������ ");
        HttpSession session = request.getSession();

        String idCarS  = request.getParameter(PARAM_NAME_SELECTED_CAR);
        String node = request.getParameter(PARAM_NAME_COMMENTS);

        //��������� ���������
        if (!Validation.parameterStringIsCorrect(idCarS)) {
            LOG.info("������ ���������: idCarS " + idCarS + "; node " + node);
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
        } else {

            Integer idCar = null;
            //�������
            try {
                idCar = Integer.parseInt(idCarS);
            } catch (NumberFormatException e) {
                LOG.info("������ ��������: idCar " + idCarS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_ADMIN;
            }

            //�������� ������
            Request userRequest =  (Request) session.getAttribute(ATTRIBUTE_USER_REQUEST);
            //id  ��������
            Integer idDriver = userRequest.getOwnerRequest();
            //id ������
            Integer idRequest = userRequest.getNamberRequest();
            //����� ������
            LocalDateTime date = userRequest.getDateDeparture();

            //����� ���� ���������
            Integer idDispatcher = (Integer) session.getAttribute(ATTRIBUTE_USERS_ID);
            Flight flight =  new Flight(date, Status.OPEN, idDriver, idDispatcher, idCar, node);

            FlightDAO dao = new FlightDAO();
            //������
            dao.create(flight, idCar, idRequest);

            session.removeAttribute(ATTRIBUTE_USER_REQUEST);
            session.removeAttribute(ATTRIBUTE_REQUESTS_CAR);
            session.setAttribute("Message", MessageManager.getProperty("message.flight.registration.successful"));
            LOG.info("������ ������ ������ ������� flight " + flight);
        }
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
