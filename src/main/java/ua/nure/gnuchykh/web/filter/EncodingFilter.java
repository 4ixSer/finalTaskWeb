package ua.nure.gnuchykh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
/**
 * Filter to set the encoding.
 * @author qny4ix
 *
 */
public final class EncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    /**
     * encoding page unf-8.
     */
    private String encoding;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.info("Change encoding " + encoding);
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
        LOG.info("Init encoding " + encoding);
        encoding = fConfig.getInitParameter("encoding");

    }

}