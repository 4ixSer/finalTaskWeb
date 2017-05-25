package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class addCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(addCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Начало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();

        CarDAO dao = new CarDAO();
        List<Car> cars= dao.findAll();

        LOG.debug("Нашли все машины.");
        session.setAttribute("cars", cars);
        LOG.info(cars);

        return ConfigurationManager.getProperty("path.page.admin");


    }

}
