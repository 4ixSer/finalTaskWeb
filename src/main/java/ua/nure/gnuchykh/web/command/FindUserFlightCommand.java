package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class FindUserFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindUserFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("������ ������ " + request.getParameter("command"));

        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("userID");
        FlightDAO dao = new FlightDAO();
        List<Flight> list = dao.findEntityByDriverId(idUser);

        LOG.debug("����� ��� ����� ��������");
        session.setAttribute("allFlight", list);
        LOG.info(list);

        return ConfigurationManager.getProperty("path.page.driver");

    }

}
