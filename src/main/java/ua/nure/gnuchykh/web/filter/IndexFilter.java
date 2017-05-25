package ua.nure.gnuchykh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.ConfigurationManager;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter(urlPatterns = { "/sdasda*" })
public class IndexFilter implements Filter {

    public IndexFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       // response.setCharacterEncoding("Cp1251");
        System.out.println("работает IndexFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("Cp1251");
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        ClientType type = (ClientType) session.getAttribute("userType");

        if (req.getParameter("command") == null) {
            if (type == ClientType.ADMINISTRATOR) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(ConfigurationManager.getProperty("path.page.admin"));
                System.err.println(" Filter=> сесия для " + type);
                System.err.println(" Filter=> Перенаправление на /jsp/work/adminPage.jsp");
                dispatcher.forward(req, resp);
                return;
            }  else if (type == ClientType.DISPATCHER) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.dispatcher"));
                System.err.println(" Filter=> сесия для " + type);
                System.err.println(" Filter=> Перенаправление на /jsp/work/adminPage.jsp");
                dispatcher.forward(req, resp);
                return;

            } else if (type == ClientType.DRIVER) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.driver"));
                System.err.println(" Filter=> сесия для " + type);
                System.err.println(" Filter=> Перенаправление на /jsp/work/adminPage.jsp");
                dispatcher.forward(req, resp);
                return;

            }

        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
