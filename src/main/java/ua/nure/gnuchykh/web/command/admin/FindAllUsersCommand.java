package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

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

/**
 * A command to search for all users in the database.
 *
 * @author qny4ix
 *
 */
public final class FindAllUsersCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы ");
        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();
        List<User> users = dao.findAll();
        session.setAttribute(ATTRIBUTE_USERS, users);
        session.setAttribute("Message", MessageManager.getProperty("message.search.all.users"));
        LOG.info("Успешный поиск всех людей.");
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
