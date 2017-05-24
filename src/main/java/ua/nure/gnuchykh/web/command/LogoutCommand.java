package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.util.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("������ ������");

        //������� �� �������� ��������.
        String page = ConfigurationManager.getProperty("path.page.index");

        LOG.trace("����������� �����: " +request.getSession().getId());
        // ����������� ������
        request.getSession().invalidate();
        return page;
    }
}