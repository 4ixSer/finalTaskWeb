package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_EMAIL;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_LOGIN;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_NAME;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_PASSWORD;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_ROLE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.HexUtil;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * A command for registering new users.
 *
 * @author qny4ix
 *
 */
public final class RegisteringUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisteringUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы ");
        HttpSession session = request.getSession();
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_USER_LOGIN);
        String pass = request.getParameter(PARAM_NAME_USER_PASSWORD);
        String email = request.getParameter(PARAM_NAME_USER_EMAIL);
        String name = request.getParameter(PARAM_NAME_USER_NAME);
        String roleS = request.getParameter(PARAM_NAME_USER_ROLE);
        // валидация параметров
        if (!Validation.parameterStringIsCorrect(login, pass, email, name, roleS)) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны: login" + login + "; pass " + pass + "; email " + email + "; name " + name + "; roleS" + roleS);
        } else {
            Integer role = null;
            // парсинг роли
            try {
                role = Integer.parseInt(roleS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга role " + roleS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_ADMIN;
            }
            // ВАлидация данных
            if (!Validation.roleIsCorrect(role) || !Validation.loginIsCorrect(login) || !Validation.mailIsCorrect(email)
                    || !Validation.passwordIsCorrect(pass)) {
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
                LOG.info("Данные не коректны: login" + login + "; pass " + pass + "; email " + email + "; name " + name + "; roleS" + roleS);
            } else {
                String passHex = HexUtil.toHex(pass);
                UserDAO dao = new UserDAO();
                // посмотрели юзера
                User user = dao.findEntityByLogin(login);
                if (user != null) {
                    // логин занят
                    session.setAttribute("Message", MessageManager.getProperty("message.user.login.exists"));
                    LOG.info("Такой логин уже используеться login " + login);
                } else {
                    // регистарция успешна
                    user = new User(login, passHex, name, email, ClientType.fromValue(role));
                    dao.create(user);
                    session.setAttribute("Message", MessageManager.getProperty("message.user.registration.successful"));
                    LOG.info("Регистрация пользователся прошла успешо user " + user);
                }
            }
        }
        return Path.PAGE_ADMIN;
    }
}
