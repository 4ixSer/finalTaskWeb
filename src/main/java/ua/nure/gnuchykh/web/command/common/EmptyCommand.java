package ua.nure.gnuchykh.web.command.common;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        /*
         * � ������ ������ ��� ������� ��������� � ����������� ������������� ��
         * �������� ����� ������
         */

        return Path.PAGE_LOGIN;
    }
}