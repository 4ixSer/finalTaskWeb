package ua.nure.gnuchykh.web.command.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.AppException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws AppException {

        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();
        String page = null;

        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        //Проверить входяшие параметры
        if (login == null || pass == null || login.isEmpty() || pass.isEmpty()||Validation.validateString(login,pass)) {
            session.setAttribute("errorMessage", MessageManager.getProperty("message.loginOrPasswordIsEmty"));
            page = Path.PAGE_INDEX;
        } else {
            // получение юзера
            User user = new UserDAO().findEntityByLogin(login);
            //проверить сосответсвтие пароля и логина с базой данных
            if (user == null || !pass.equals(user.getPassword())) {
                LOG.info("Запрашиваемого юзера не сушествует или неправильный пароль");
                session.setAttribute("errorMessage",
                        MessageManager.getProperty("message.cannotFindUserWithLoginOrPassword"));
                page = Path.PAGE_INDEX;
            } else {
                //создание сесии
                session.setAttribute("userType", user.getType());
                session.setAttribute("name", user.getName());
                session.setAttribute("userID", user.getId());

                // проверка на установленный язык;
                if (session.getAttribute("language") == null) {
                    session.setAttribute("language", Locale.getDefault());
                }

                LOG.debug("Открытие сесии для " + user.getType() + "; login= " + user.getLogin() + "; idSession= "
                        + session.getId() + "; language= " + session.getAttribute("language"));

                page = Path.getPage(user.getType());
            }
        }
        LOG.info("Отправлено на " + page);
        return page;
    }
}