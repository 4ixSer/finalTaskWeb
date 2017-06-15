package ua.nure.gnuchykh.web.command.common;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * Command to close the table.
 *
 * @author qny4ix
 *
 */
public final class CloseCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CloseCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("НАчало работы ");
        HttpSession session = request.getSession();
        String table = request.getParameter("table");

        if (!Validation.parameterStringIsCorrect(table) || !Validation.validateString(table)) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны table " + table);
        } else {
            session.removeAttribute(table);
            session.setAttribute("Message", MessageManager.getProperty("message.close"));
            LOG.info("Закрытие таблицы " + table);
        }
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));
    }
}
