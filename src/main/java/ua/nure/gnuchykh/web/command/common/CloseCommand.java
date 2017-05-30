package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class CloseCommand  implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CloseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("������ ������ " + request.getParameter("command"));

        HttpSession session = request.getSession();
        String table = request.getParameter("table");

        if(!Validation.validateString(table)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("������ �� ��������");
            return Path.PAGE_ADMIN;
        }

        session.removeAttribute(table);
        LOG.info("�������� ������� " +table);
        session.setAttribute("Message", MessageManager.getProperty("message.closeTable"));
        return Path.getPage((ClientType) session.getAttribute("userType"));

    }

}
