package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class ÑhangeLanguageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ÑhangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        LOG.info("language " + language);
        LOG.info("Ñòğàíèöà îò êóäà ïğèøåë çàïğîñ " + request.getRequestURI());

        return Path.getPage((ClientType) session.getAttribute("userType"));

    }
}
