package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.User;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class FindCommand  implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        System.err.println("����� � FindCommand");

        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();

        List<User> users= dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        session.setAttribute("users", users);



        return ConfigurationManager.getProperty("path.page.admin");


    }

}