package ua.nure.gnuchykh.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // TODO удалить потом
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.trace("Log4j has been initialized");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes CommandContainer.
     *
     * @param servletContext
     */
    private void initCommandContainer() {
        // TODO удалить потом
    }

}