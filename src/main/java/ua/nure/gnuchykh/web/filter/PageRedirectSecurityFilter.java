package ua.nure.gnuchykh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = { "/jsp/*" }, initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class PageRedirectSecurityFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(PageRedirectSecurityFilter.class);
    private String indexPath;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        indexPath = fConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.err.println(httpRequest.getRequestURI());
        if (httpRequest.getRequestURI().equals("/WEB/jsp/error/error.jsp")) {

            LOG.info("переход на " + httpRequest.getContextPath() + indexPath);

        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}