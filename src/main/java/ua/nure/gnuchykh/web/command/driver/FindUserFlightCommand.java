package ua.nure.gnuchykh.web.command.driver;

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

public class FindUserFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindUserFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("userID");

        FlightDAO dao = new FlightDAO();
        List<Flight> list = dao.findEntityByDriverId(idUser);

        LOG.debug("Нашли все рейсы водителя");
        session.setAttribute("allFlight", list);
        session.setAttribute("Message", MessageManager.getProperty("message.findUserFlight"));

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
