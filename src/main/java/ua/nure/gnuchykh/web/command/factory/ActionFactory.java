package ua.nure.gnuchykh.web.command.factory;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.client.CommandEnum;
import ua.nure.gnuchykh.web.command.common.EmptyCommand;

public final class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        // ���������� ����� ������� �� �������
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            // ���� ������� �� ������ � ������� �������
            return current;
        }
        // ��������� �������, ���������������� �������
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("Message", action + MessageManager.getProperty("message.parameter.incorrect.format"));
        }
        return current;
    }
}