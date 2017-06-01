package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
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


public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы ");
        HttpSession session = request.getSession();
        String idS = request.getParameter(PARAM_NAME_ID);

        // Валилация
        if (!Validation.parameterStringIsCorrect(idS)) {
            session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
        } else {

            Integer id = null;

            // Парсинг
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка валидации");
                session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
                return Path.PAGE_ADMIN;
            }

            UserDAO dao = new UserDAO();
            dao.delete(id);

            LOG.info("Удаление юзера");

            List<User> users = dao.findAll();

            LOG.debug("Нашли всех зареистрированыых юзерров.");
            session.setAttribute(ATTRIBUTE_USERS, users);
            session.setAttribute("Message", MessageManager.getProperty("message.user.delete"));

        }
        return Path.PAGE_ADMIN;
    }

}
