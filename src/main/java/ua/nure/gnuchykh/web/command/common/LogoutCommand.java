package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class LogoutCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы ");

        // уничтожение сессии
        LOG.trace("Уничтожение сесии: " + request.getSession().getId());

        request.getSession().invalidate();
        return Path.PAGE_INDEX;
    }
}