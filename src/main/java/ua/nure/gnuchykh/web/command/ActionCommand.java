package ua.nure.gnuchykh.web.command;

import javax.servlet.http.HttpServletRequest;

import ua.nure.gnuchykh.exception.DBException;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws DBException;
}