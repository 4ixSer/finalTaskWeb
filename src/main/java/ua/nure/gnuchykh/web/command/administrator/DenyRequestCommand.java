package ua.nure.gnuchykh.web.command.administrator;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_REQUESTS_CAR;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class DenyRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Ќачало работы");
        HttpSession session = request.getSession();

        RequestDAO dao = new RequestDAO();
        //нашли за€вку в сессии
        Request requestUser = (Request) session.getAttribute(ATTRIBUTE_USER_REQUEST);

        //помен€ть статут на отказать
        requestUser.setStatus(Status.REJEJECTED);
        // обновить статут в бд
        dao.update(requestUser);

        //удалить атрибуты с сесии
        session.removeAttribute(ATTRIBUTE_USER_REQUEST);
        session.removeAttribute(ATTRIBUTE_REQUESTS_CAR);
        session.setAttribute("Message", MessageManager.getProperty("message.request.cancel"));
        LOG.info("«а€вка на рейс отклонена.");

        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));

    }
}
