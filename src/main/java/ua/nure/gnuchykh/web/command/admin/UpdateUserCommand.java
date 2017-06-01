package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_EMAIL;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_NAME;

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



public class UpdateUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы ");
        HttpSession session = request.getSession();
        String idS = request.getParameter(PARAM_NAME_ID);
        String name = request.getParameter(PARAM_NAME_USER_NAME);
        String email = request.getParameter(PARAM_NAME_USER_EMAIL);
        //Начальная валидация
        if (!Validation.parameterStringIsCorrect(idS, name, email)||!Validation.mailIsCorrect(email)||
                !Validation.validateString(name)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            System.out.println(idS+" "+name+" "+email);
            LOG.info("Данные не коректны");
        } else {
            //парсинг
            Integer id = null;
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка валидации");
                session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
                return Path.PAGE_ADMIN;
            }

            UserDAO dao = new UserDAO();
            User user = null;

            List<User> list = (List<User>) session.getAttribute("users");

            for (User users : list) {
                if (users.getId() == id) {
                    users.setEmail(email);
                    users.setName(name);
                    user = users;
                }
            }
            user = dao.update(user);
            session.setAttribute(ATTRIBUTE_USERS, list);
            session.setAttribute("Message", MessageManager.getProperty("message.user.update"));
            LOG.info("Изминения успешно внесены в базу данных");
        }

        return Path.PAGE_ADMIN;
    }
}

