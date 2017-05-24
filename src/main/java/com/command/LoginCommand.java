package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.entity.User;
import com.manager.LoginManager;
import com.util.ConfigurationManager;
import com.util.MessageManager;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        System.err.println("LoginCommand =====> ");

        String page = null;

        // ���������� �� ������� ������ � ������
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        //��������� ������
        User user = LoginManager.getUSer(login);
        System.err.println(user);
        // �������� ������ � ������
        if(user == null||LoginManager.loginIsFalse(user, pass)) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");

        } else {
            HttpSession session = request.getSession();
            request.setAttribute("user", user.getType());
            //TODO ��������� ������������ ������
               //��������� ������
//            request.setAttribute("language", Locale.getDefault());
            System.err.println(" Login=> ������ � ����� �������� " + user.getType());
            session.setAttribute("userType", user.getType());

            // ����������� ���� � main.jsp
            page = getPageRole(user.getType().toString());
        }


      /* ����     if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute("user", ClientType.ADMINISTRATOR);
            HttpSession session = request.getSession();
            System.err.println(" Login=> ������ � ����� �������� " + ClientType.ADMINISTRATOR);
            session.setAttribute("userType", ClientType.ADMINISTRATOR);
            // ����������� ���� � main.jsp
            String role = request.getParameter(PARAM_NAME_ROLE);
            page = getPageRole(role);
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

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
//        System.out.println(propertiesName);
        return ConfigurationManager.getProperty(propertiesName);

    }

}