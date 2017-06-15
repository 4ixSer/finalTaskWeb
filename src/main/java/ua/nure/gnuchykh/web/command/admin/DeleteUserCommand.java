package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * The command to delete the user.
 *
 * @author qny4ix
 *
 */
public final class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы ");
        HttpSession session = request.getSession();
        String idS = request.getParameter(PARAM_NAME_ID);

        // проверка на null
        if (!Validation.parameterStringIsCorrect(idS)) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны idS " + idS);
        } else {

            Integer id = null;
            // Парсинг
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга id" + idS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_ADMIN;
            }
            if (id == session.getAttribute(ATTRIBUTE_USERS_ID)) {
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
                LOG.info("Нельзя удалить " + id);
            } else {
                UserDAO dao = new UserDAO();
                dao.delete(id);
                List<User> users = dao.findAll();
                session.setAttribute(ATTRIBUTE_USERS, users);
                session.setAttribute("Message", MessageManager.getProperty("message.user.delete.successful"));
                LOG.info("Successfully removed the user id " + id);
            }

        }
        return Path.PAGE_ADMIN;
    }

}
