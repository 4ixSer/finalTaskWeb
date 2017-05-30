package ua.nure.gnuchykh.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindAllCarsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllCarsCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();

        CarDAO dao = new CarDAO();
        List<Car> cars = dao.findAll();

        LOG.debug("Нашли все машины.");
        session.setAttribute("cars", cars);
        session.setAttribute("Message", MessageManager.getProperty("message.findCar"));

        return Path.getPage((ClientType) session.getAttribute("userType"));
    }
}
