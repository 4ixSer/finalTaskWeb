package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("������ ������");

        // TODO ��������� ������
        Integer id = Integer.valueOf(request.getParameter("id"));

        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();

        LOG.info("������ �������� " + dao.delete(id));

        List<User> users = dao.findAll();

        LOG.debug("����� ���� ����������������� �������.");
        session.setAttribute("users", users);

        return ConfigurationManager.getProperty("path.page.admin");

    }

}