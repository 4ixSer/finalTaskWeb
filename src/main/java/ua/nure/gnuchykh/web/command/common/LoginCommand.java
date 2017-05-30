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

        LOG.info("������ ������ " + request.getParameter("command"));
        HttpSession session = request.getSession();
        String page = null;

        // ���������� �� ������� ������ � ������
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        //��������� �������� ���������
        if (login == null || pass == null || login.isEmpty() || pass.isEmpty()||
                !Validation.loginIsCorrect(login)||!Validation.passwordIsCorrect(pass)) {
            session.setAttribute("errorMessage", MessageManager.getProperty("message.loginOrPasswordIsEmty"));
            LOG.info("��������� ������ �� ���������");
            page = Path.PAGE_INDEX;
        } else {
            // ��������� �����
            User user = new UserDAO().findEntityByLogin(login);
            //��������� ������������� ������ � ������ � ����� ������
            if (user == null || !pass.equals(user.getPassword())) {
                LOG.info("�������������� ����� �� ���������� ��� ������������ ������");
                session.setAttribute("errorMessage",
                        MessageManager.getProperty("message.cannotFindUserWithLoginOrPassword"));
                page = Path.PAGE_INDEX;
            } else {
                //�������� �����
                session.setAttribute("userType", user.getType());
                session.setAttribute("name", user.getName());
                session.setAttribute("userID", user.getId());

                // �������� �� ������������� ����;
                if (session.getAttribute("language") == null) {
                    session.setAttribute("language", Locale.getDefault());
                }

                LOG.debug("�������� ����� ��� " + user.getType() + "; login= " + user.getLogin() + "; idSession= "
                        + session.getId() + "; language= " + session.getAttribute("language"));

                page = Path.getPage(user.getType());
            }
        }
        return page;
    }
}