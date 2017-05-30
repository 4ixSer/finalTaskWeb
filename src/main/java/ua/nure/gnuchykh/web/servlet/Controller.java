package ua.nure.gnuchykh.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.exception.AppException;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.factory.ActionFactory;

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
        LOG.info("Начало рабоыт");


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
        } catch (AppException e) {
            //TODO тут передалать на сесию или куки
            request.setAttribute("errorMessage", e.getMessage());
        }
        // метод возвращает страницу ответа
        // page = null; // поэксперементировать!
        if (page != null) {
            // RequestDispatcher dispatcher =
            // getServletContext().getRequestDispatcher(page);
            // вызов страницы ответа на запрос
            // dispatcher.forward(request, response);

            LOG.trace("Перход на станицу: " + page);

            response.sendRedirect(page);
        } else {
            LOG.trace("ветка ошибки");
            // установка страницы c cообщением об ошибке
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(page);
        }
    }

    @Override
    public void init() throws ServletException {

    }

}