package ua.nure.gnuchykh.web.command.driver;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_ALL_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * A command for finding user requests.
 *
 * @author qny4ix
 *
 */
public final class FindRequestByUserId implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestByUserId.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы ");

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute(ATTRIBUTE_USERS_ID);

        RequestDAO dao = new RequestDAO();
        List<Request> userReques = dao.findEntityByUserId(idUser);
        if (userReques.isEmpty()) {
            session.setAttribute("Message", MessageManager.getProperty("message.request.null"));
            LOG.debug("Заявок больше нет.");
        } else {
            session.setAttribute(ATTRIBUTE_ALL_REQUEST, userReques);
            session.setAttribute("Message", MessageManager.getProperty("message.search.all.request"));
            LOG.debug("Нашли все заявки водителя");
        }

        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
