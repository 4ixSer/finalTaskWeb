package ua.nure.gnuchykh.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.factory.ActionFactory;
/**
 * The main servlet that manages all command.
 * @author qny4ix
 *
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 4604597013190449264L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("Начало работы");
        String page = null;
        // определение команды, пришедшей из JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*
         * вызов реализованного метода execute() и передача параметров
         * классу-обработчику конкретной команды
         */
        try {
            page = command.execute(request);
            LOG.trace("Перход на станицу: " + page);
            response.sendRedirect(page);
        } catch (DBException e) {
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", e.getMessage());
            LOG.info(e.getMessage());
            response.sendRedirect(Path.PAGE_ERROR);
        }

    }


}