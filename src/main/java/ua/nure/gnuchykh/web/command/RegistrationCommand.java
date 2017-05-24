package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.manager.RegistationManager;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.util.MessageManager;

public class RegistrationCommand implements ActionCommand  {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) {
        System.err.println("зашли в RegistrationCommand");

//        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String name = request.getParameter(PARAM_NAME_NAME);
        Integer role = Integer.parseInt(request.getParameter(PARAM_NAME_ROLE));
        // регистрация прошла не успешно
        if(!RegistationManager.registaticonNewUser(login, pass,name,email,role)) {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation"));
        } else {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));
        }


        return ConfigurationManager.getProperty("path.page.admin");


    }

}
