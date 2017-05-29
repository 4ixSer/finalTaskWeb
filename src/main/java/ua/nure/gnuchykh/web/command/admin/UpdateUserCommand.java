package ua.nure.gnuchykh.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class UpdateUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));
        LOG.info(request.getParameter("id")+" "+request.getParameter("name")+" "+request.getParameter("email"));
        Integer id =Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        UserDAO dao = new UserDAO();

        HttpSession session =request.getSession();
        User user = null;

        List<User> list = (List<User>) session.getAttribute("users");

        for (User users : list) {
            if(users.getId()==id) {

                users.setEmail(email);
                users.setName(name);
                user=users;

            }
        }
        LOG.info(user);
        session.setAttribute("users", list);

        user = dao.update(user);

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
