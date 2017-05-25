package ua.nure.gnuchykh.web.command;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.manager.LoginManager;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.util.MessageManager;

public class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Ќачало работы " + request.getParameter("command"));

        String page = null;

        // TODO валидацию входных парметров
        // извлечение из запроса логина и парол€
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        // получение логина
        User user = LoginManager.getUSer(login);

        // проверка логина и парол€
        if (user == null || LoginManager.loginIsFalse(user, pass)) {

            LOG.debug("ёзера с login не сушествует");

            // передалать отправку ошибки.
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");

        } else {

            HttpSession session = request.getSession();
            request.setAttribute("user", user.getType());
            // request.setAttribute("language", "en_US");

            // TODO придумать переключение €зыков
            // получение локали
            // request.setAttribute("language", Locale.getDefault());

            session.setAttribute("userType", user.getType());
            session.setAttribute("name", user.getName());
            session.setAttribute("language", Locale.getDefault());
            LOG.debug("ќткрытие сесии дл€ " + user.getType() + "; login= " + user.getLogin() + "; password= "
                    + user.getPassword() + "; idSession= " + session.getId());

            // определение пути к main.jsp
            page = getPageRole(user.getType().toString());
        }

        /*
         * было if (LoginLogic.checkLogin(login, pass)) {
         * request.setAttribute("user", ClientType.ADMINISTRATOR); HttpSession
         * session = request.getSession();
         * System.err.println(" Login=> «апись в сесию значени€ " +
         * ClientType.ADMINISTRATOR); session.setAttribute("userType",
         * ClientType.ADMINISTRATOR); // определение пути к main.jsp String role
         * = request.getParameter(PARAM_NAME_ROLE); page = getPageRole(role); }
         * else { request.setAttribute("errorLoginPassMessage",
         * MessageManager.getProperty("message.loginerror")); page =
         * ConfigurationManager.getProperty("path.page.login"); }
         *
         */
        return page;
    }

    private String getPageRole(String role) {

        String propertiesName = null;

        switch (role) {
        case "ADMINISTRATOR":
            propertiesName = "path.page.admin";
            break;
        case "DISPATCHER":
            propertiesName = "path.page.dispatcher";
            break;
        case "DRIVER":
            propertiesName = "path.page.driver";
            break;
        default:
            propertiesName = "path.page.login";
            System.out.println("Eroor");
            break;
        }

        return ConfigurationManager.getProperty(propertiesName);

    }

}