package ua.nure.gnuchykh.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindAllUsersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();
        List<User> users = dao.findAll();

        LOG.debug("Нашли всех зареистрированыых юзерров.");
        session.setAttribute("users", users);
        session.setAttribute("Message", MessageManager.getProperty("message.findUser"));

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
