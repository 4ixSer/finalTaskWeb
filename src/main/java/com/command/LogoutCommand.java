package com.command;

import javax.servlet.http.HttpServletRequest;

import com.util.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.index");
        System.err.println("”ничтожение данных");
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}