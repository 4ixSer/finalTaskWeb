package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class DenyRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DenyRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Начало работы"+ request.getParameter("command") );

        // TODO валидация данных
        Integer id = Integer.valueOf(request.getParameter("id"));

        HttpSession session = request.getSession();
        RequestDAO dao = new RequestDAO();
        Request requestUser = (Request) session.getAttribute("requestUser");
        requestUser.setStatus(Status.REJEJECTED);

        LOG.info("Статус обновления " + dao.update(requestUser));

        session.removeAttribute("requestUser");
        session.removeAttribute("requestCar");

        return ConfigurationManager.getProperty("path.page.dispatcher");

    }



}
