package com.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.command.ActionCommand;
import com.command.EmptyCommand;
import com.command.client.CommandEnum;
import com.util.MessageManager;

public class ActionFactory {
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
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}