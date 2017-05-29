package ua.nure.gnuchykh.web.command.driver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindRequestByUserId implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestByUserId.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();

        RequestDAO dao = new  RequestDAO();
        List<Request> userReques = dao.findEntityByUserId((Integer) session.getAttribute("userID"));


        session.setAttribute("userRequest", userReques);

        return ConfigurationManager.getProperty("path.page.driver");

    }

}
