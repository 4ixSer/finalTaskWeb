package ua.nure.gnuchykh.web.command;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class FindRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();

        RequestDAO dao = new RequestDAO();
        LOG.info("LocalDateTime.now() =" +LocalDateTime.now());
        Request requestUser = dao.findFirstRequest(LocalDateTime.now());
        if(requestUser== null) {
            LOG.info("Send redirect на эро пейд");

        } else {
            LOG.info("request "+requestUser);

            session.setAttribute("requestUser", requestUser);

        }
        return ConfigurationManager.getProperty("path.page.dispatcher");

    }

}
