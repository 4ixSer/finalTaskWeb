package ua.nure.gnuchykh.web.command.driver;

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

public class FindRequestByUserId implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestByUserId.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("userID");

        RequestDAO dao = new  RequestDAO();
        List<Request> userReques = dao.findEntityByUserId(idUser);
        LOG.debug("Нашли все заявки водителя");
        session.setAttribute("userRequest", userReques);
        session.setAttribute("Message", MessageManager.getProperty("message.findUserRequest"));

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
