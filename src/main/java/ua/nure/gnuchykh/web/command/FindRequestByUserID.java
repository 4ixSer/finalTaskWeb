package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class FindRequestByUserID implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestByUserID.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();

        RequestDAO dao = new  RequestDAO();
        List<Request> userReques = dao.findEntityByUserId((Integer) session.getAttribute("userID"));

        LOG.info("Нашли всех заявки юзера.");
        for (Request request2 : userReques) {
            LOG.info(request2);
        }
        session.setAttribute("userRequest", userReques);

        return ConfigurationManager.getProperty("path.page.driver");

    }

}
