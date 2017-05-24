package com.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.entity.User;
import com.util.ConfigurationManager;

public class FindCommand  implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        System.err.println("зашли в FindCommand");

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
