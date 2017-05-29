package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class FindALLRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindALLRequestCommand.class);
    //ÍÅÃÎÒÎÂ ÇÀÃÎÒÎÂÊÀ ÄË‗ ×ÅÃÎÒÎ
    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Íאקאכמ נאבמעû " + request.getParameter("command"));

        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();
        List<User> users = dao.findAll();

        LOG.debug("Íארכט גסוץ חאנוטסענטנמגאםûûץ ‏חוננמג.");
        session.setAttribute("users", users);

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
