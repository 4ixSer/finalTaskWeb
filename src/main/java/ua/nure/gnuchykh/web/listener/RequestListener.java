package ua.nure.gnuchykh.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebListener
public final class RequestListener implements ServletRequestListener {

    private static final Logger LOG = Logger.getLogger(RequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent ev) {

        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
       LOG.info("Request Initialized for " + req.getRequestURI());

    }

    @Override
    public void requestDestroyed(ServletRequestEvent ev) {

    }
}