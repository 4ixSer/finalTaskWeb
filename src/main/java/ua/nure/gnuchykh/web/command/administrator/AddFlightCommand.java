package ua.nure.gnuchykh.web.command.administrator;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class AddFlightCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddFlightCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы " + request.getParameter("command"));

        Integer idCar = Integer.parseInt(request.getParameter("selectedCar"));

        String node = request.getParameter("node");

        LocalDateTime localDateNow = LocalDateTime.now();

        HttpSession session = request.getSession();

        Request userRequest =  (Request) session.getAttribute("requestUser");
        Integer idUSer =userRequest.getOwnerRequest();

        Integer idDispatcher = (Integer) session.getAttribute("userID");
        Car car = null;
        List<Car> cars =(List<Car>) session.getAttribute("requestCar");
        for (Car newCar : cars) {
            if(newCar.getId()==idCar) {
                car=newCar;
            }
        }

        car.setStatusCar(ua.nure.gnuchykh.entity.cars.Status.USED);


        FlightDAO dao = new FlightDAO();

        Flight flight =  new Flight(localDateNow, Status.OPEN, idUSer, idDispatcher, idCar, node);

        if (dao.create(flight)) {

            LOG.info("Запись заявки прошла успешно");
            RequestDAO requestDAO = new RequestDAO();
            CarDAO carDAO = new CarDAO();

            LOG.info("Статус удаления запроса"+  requestDAO.delete(userRequest));
            LOG.info("Статус изменения статуса машины"+  carDAO.update(car));
            //TODO тут нужно както придумать чтобы обе команды выполнялись создания рейса - удаления запроса - изминения статуса ммашыны
            session.removeAttribute("requestUser");
            return getPageRole(session.getAttribute("userType").toString());

        } else {
            return ConfigurationManager.getProperty("path.page.login");
        }
    }

    private String getPageRole(String role) {

        String propertiesName = null;

        switch (role) {
        case "ADMINISTRATOR":
            propertiesName = "path.page.admin";
            break;
        case "DISPATCHER":
            propertiesName = "path.page.dispatcher";
            break;
        case "DRIVER":
            propertiesName = "path.page.driver";
            break;
        default:
            propertiesName = "path.page.login";
            System.out.println("Eroor");
            break;
        }

        return ConfigurationManager.getProperty(propertiesName);

    }


}
