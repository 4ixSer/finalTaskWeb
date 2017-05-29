package ua.nure.gnuchykh.web.command.driver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class UpdateFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateFlightCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Начало работы " + request.getParameter("command"));
        HttpSession session = request.getSession();
        Integer idFlight = Integer.parseInt(request.getParameter("idFlight"));
        Integer statusCar = Integer.parseInt(request.getParameter("statusCar"));
        CarDAO carDAO = new CarDAO();
        FlightDAO flightDAO = new FlightDAO();


      //TODO эти команды должны работаь вместе




        Flight flight = null;
        List<Flight> list = (List<Flight>) session.getAttribute("allFlight");
        for (Flight flight2 : list) {
            if(flight2.getNamberFlight()==idFlight) {
                flight2.setStatus(ua.nure.gnuchykh.entity.subject.Status.CLOSED);
                flight=flight2;
            }
        }

        Car car =carDAO.findEntityById(flight.getCar());
        LOG.info("Статус "+Status.fromValue(statusCar));
        car.setStatusCar(Status.fromValue(statusCar));

        LOG.info("Обновленная машина "+ carDAO.update(car));
        if(flightDAO.update(flight)==null) {
            session.setAttribute("allFlight", list);
        }

        //TODO переделать


        return ConfigurationManager.getProperty("path.page.driver");
    }

}
