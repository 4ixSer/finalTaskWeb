package ua.nure.gnuchykh.web.command.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.manager.RegistationManager;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.util.MessageManager;
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

        LOG.info("������ ������ " + request.getParameter("command"));

        // String page = null;
        // ���������� �� ������� ������ � ������
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(PARAM_NAME_NAME);
        Integer role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE));
        // ����������� ������ �� �������
        if (!RegistationManager.registaticonNewUser(login, pass, name, email, role)) {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation"));
        } else {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));
        }

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
