package ua.nure.gnuchykh.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class RegisteringUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(RegisteringUserCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(PARAM_NAME_NAME);
        Integer role = null;
        // если число не прошло
        try {
            role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE));
        } catch (NumberFormatException e) {
            LOG.info("Ошибка валидации");
            session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
            return Path.PAGE_ADMIN;
        }
        //ВАлидация данных
        if (login == null || pass == null || login.isEmpty() || pass.isEmpty()||
                email == null || name == null || email.isEmpty() || name.isEmpty()||
                        !Validation.roleIsCorrect(role) ||!Validation.loginIsCorrect(login)||
                        !Validation.mailIsCorrect(email)||!Validation.passwordIsCorrect(pass)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("Данные не коректны");
            return Path.PAGE_ADMIN;
        }

        UserDAO dao = new UserDAO();
        // посмотрели юзера
        User user = dao.findEntityByLogin(login);
        if (user != null) {
            //логин занят
            session.setAttribute("Message", MessageManager.getProperty("message.loginExists"));
            LOG.info("Такой логин уже есть");
            return Path.PAGE_ADMIN;
        } else {
            //регистарция успешна
            user = new User(login, pass, name, email, ClientType.fromValue(role));
            dao.create(user);
            session.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));
            LOG.info("Регистрация user прошла успещкно");
            return Path.PAGE_ADMIN;
        }
    }

}
