package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class CancelRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CancelRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Начало работы"+ request.getParameter("command") );

        // TODO валидация данных
        Integer id = Integer.valueOf(request.getParameter("id"));

        HttpSession session = request.getSession();

        RequestDAO dao = new RequestDAO();

        LOG.info("Статус удаления " + dao.delete(id));

        List<Request> list = dao.findEntityByUserId((Integer) session.getAttribute("userID"));

        session.setAttribute("userRequest", list);

        return ConfigurationManager.getProperty("path.page.driver");

    }

}
