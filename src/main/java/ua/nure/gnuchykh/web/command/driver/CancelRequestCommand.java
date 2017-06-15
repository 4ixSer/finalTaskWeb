package ua.nure.gnuchykh.web.command.driver;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_ALL_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * The command to cancel the Request by the user.
 * If the Request is not yet considered.
 *
 * @author qny4ix
 *
 */
public final class CancelRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CancelRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы");
        String idS = request.getParameter(PARAM_NAME_ID);
        HttpSession session = request.getSession();

        if (!Validation.parameterStringIsCorrect(idS)) {
            LOG.info("Ошибка валидации: idS " + idS);
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
        } else {
            Integer id = null;

            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка валидации: id " + idS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_DRIVER;
            }

            RequestDAO dao = new RequestDAO();
            Request requestUser = dao.findEntityById(id);
            if (requestUser==null) {
                LOG.info("Заявка № " + id + " нету.");
            } else if (requestUser.getStatus() == Status.PROCESSED) {
                session.setAttribute("Message", MessageManager.getProperty("message.request.processed"));
                LOG.info("Заявка № " + id + " обрабатываеться");
            } else {
                dao.delete(id);

                List<Request> list = dao.findEntityByUserId((Integer) session.getAttribute(ATTRIBUTE_USERS_ID));
                session.setAttribute(ATTRIBUTE_ALL_REQUEST, list);
                session.setAttribute("Message", MessageManager.getProperty("message.request.delete.successful"));
                LOG.info("Заявка № " + id + " успешно отменена");
            }
        }
        return Path.PAGE_DRIVER;
    }
}
