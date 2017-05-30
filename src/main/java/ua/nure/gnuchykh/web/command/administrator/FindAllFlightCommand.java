package ua.nure.gnuchykh.web.command.administrator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindAllFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();

        FlightDAO dao = new FlightDAO();
        List<Flight> list = dao.findAll();

        LOG.debug("Нашли все рейсы.");
        session.setAttribute("allFlight", list);
        session.setAttribute("Message", MessageManager.getProperty("message.findFlight"));

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
