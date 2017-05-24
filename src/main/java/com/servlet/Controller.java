package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.command.ActionCommand;
import com.command.factory.ActionFactory;
import com.util.ConfigurationManager;
import com.util.MessageManager;


@WebServlet("/controller")
public class Controller extends HttpServlet {

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
//        request.setCharacterEncoding("utf-16");
        request.setCharacterEncoding("cp1251");

        String page = null;
        // ����������� �������, ��������� �� JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*
         * ����� �������������� ������ execute() � �������� ����������
         * ������-����������� ���������� �������
         */
        page = command.execute(request);
        // ����� ���������� �������� ������
        // page = null; // ��������������������!
        if (page != null) {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // ����� �������� ������ �� ������
//            dispatcher.forward(request, response);
            System.err.println("++++++++++++++++++++++");
            LOG.trace("�������� page--> " + page);
            System.err.println("++++++++++++++++++++++");

            response.sendRedirect(page);
        } else {
            // ��������� �������� c c��������� �� ������
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(page);
        }
    }

    @Override
    public void init() throws ServletException {

    }


}