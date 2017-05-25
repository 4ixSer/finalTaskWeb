package ua.nure.gnuchykh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gnuchykh.entity.users.ClientType;

//TODO тут исправить
@WebFilter(urlPatterns = { "/controllerвыфвфыв" }, servletNames = { "MainServlet" })
public class ServletSecurityFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding("Cp1251");
        HttpSession session = req.getSession();
        ClientType type = (ClientType) session.getAttribute("userType");
        if (type == null) {
            type = ClientType.GUEST;
        }

        System.err.println(" Filter=> Данные сесии " + type);
        // if (req.getParameter("command")==null) {
        // if (type == ClientType.ADMINISTRATOR) {
        // RequestDispatcher dispatcher =
        // request.getServletContext().getRequestDispatcher("/jsp/work/adminPage.jsp");
        // System.err.println(" Filter=> сесия для " + type);
        // System.err.println(" Filter=> Перенаправление на
        // /jsp/work/adminPage.jsp");
        // dispatcher.forward(req, resp);
        // return;
        //
        // } else if (type == ClientType.DISPATCHER) {
        // RequestDispatcher dispatcher =
        // request.getServletContext().getRequestDispatcher("/jsp/work/dispatcherPage.jsp");
        // System.err.println(" Filter=> сесия для " + type);
        // System.err.println(" Filter=> Перенаправление на
        // /jsp/work/adminPage.jsp");
        // dispatcher.forward(req, resp);
        // return;
        //
        // } else if (type == ClientType.DRIVER) {
        // RequestDispatcher dispatcher =
        // request.getServletContext().getRequestDispatcher("/jsp/work/driverPage.jsp");
        // System.err.println(" Filter=> сесия для " + type);
        // System.err.println(" Filter=> Перенаправление на
        // /jsp/work/adminPage.jsp");
        // dispatcher.forward(req, resp);
        // return;
        //
        // }
        // }

        // if (type == null) {
        // type = ClientType.GUEST;
        // session.setAttribute("userType", type);
        // RequestDispatcher dispatcher =
        // request.getServletContext().getRequestDispatcher("/jsp/login.jsp");
        // System.err.println(" Filter=> сесия для гостя "+ type);
        // System.err.println(" Filter=> Перенаправление на /jsp/login.jsp");
        // dispatcher.forward(req, resp);
        // return;
        // }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }
}