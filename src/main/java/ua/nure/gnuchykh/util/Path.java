package ua.nure.gnuchykh.util;

import ua.nure.gnuchykh.entity.users.ClientType;

/**
 * Path holder (jsp pages).
 *
 *
 */
public final class Path {
    // pages
    public static final String PAGE_INDEX = "/WEB/index.jsp";
    public static final String PAGE_LOGIN = "/WEB/jsp/login.jsp";
    public static final String PAGE_ADMIN = "/WEB/jsp/work/adminPage.jsp";
    public static final String PAGE_DISPATCHER = "/WEB/jsp/work/dispatcherPage.jsp";
    public static final String PAGE_DRIVER = "/WEB/jsp/work/driverPage.jsp";
    public static final String PAGE_ERROR = "/WEB/jsp/error/error.jsp";

    public static String getPage(ClientType type) {


        if(type == null)
            return PAGE_INDEX;

        String page = null;
        // TODO Подумать как красивей это поменять
        switch (type.value()) {
        case 1:
            page = PAGE_ADMIN;
            break;
        case 2:
            page = PAGE_DISPATCHER;
            break;
        case 3:
            page = PAGE_DRIVER;
            break;
        default:
            page = PAGE_INDEX;
            break;
        }

        return page;

    }
}