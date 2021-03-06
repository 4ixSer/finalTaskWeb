package ua.nure.gnuchykh.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.MessageManager;
/**
 * Filter for filtering access to commands.
 * @author qny4ix
 *
 */
public final class AccessFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    /**
     * MAP for storing the client type and its possible commands.
     */
    private static Map<ClientType, List<String>> accessMap = new HashMap<ClientType, List<String>>();

    /**
     * List of commands.
     */
    private static List<String> commons = new ArrayList<String>();
    /**
     * List of commands that do not need to be monitored.
     */
    private static List<String> outOfControl = new ArrayList<String>();

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (accessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            httpRequest.getSession().setAttribute("errorMessage", MessageManager.getProperty("message.access.error"));
            LOG.trace("Set the request attribute: errorMessage --> " + MessageManager.getProperty("message.access.error"));

            HttpServletResponse httpresponse = (HttpServletResponse) response;
            httpresponse.sendRedirect("/WEB/jsp/error/error.jsp");

        }
    }

    private boolean accessAllowed(ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        LOG.trace("Command name " + commandName);
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        if (outOfControl.contains(commandName)) {
            return true;
        }
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }
        ClientType userRole = (ClientType) session.getAttribute("userType");
        if (userRole == null) {
            return commons.contains(commandName);
        }
        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // ����� �����
        accessMap.put(ClientType.ADMINISTRATOR, asList(fConfig.getInitParameter("admin")));
        accessMap.put(ClientType.DISPATCHER, asList(fConfig.getInitParameter("dispatcher")));
        accessMap.put(ClientType.DRIVER, asList(fConfig.getInitParameter("driver")));
        LOG.trace("Access map --> " + accessMap);

        // ����� ����� ������
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // ����� �� �������������� ������
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str
     *            parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(final String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}