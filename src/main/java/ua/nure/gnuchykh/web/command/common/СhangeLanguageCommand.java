package ua.nure.gnuchykh.web.command.common;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_lANGUAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class �hangeLanguageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(�hangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        //TODO ��� ��� �� ��
        LOG.info("������ ������ ");
        HttpSession session = request.getSession();
        String language = request.getParameter(ATTRIBUTE_lANGUAGE);
        session.setAttribute(ATTRIBUTE_lANGUAGE, language);
        LOG.info("language " + language);

        return Path.getPage((ClientType) session.getAttribute("userType"));

    }
}
