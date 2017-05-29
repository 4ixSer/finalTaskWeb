package ua.nure.gnuchykh.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class UpdateCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateCarCommand.class);

    private static final String PARAM_NAME_NAMBER = "namber";
    private static final String PARAM_NAME_TYPE = "newtype";
    private static final String PARAM_NAME_CARRYING = "carrying";
    private static final String PARAM_NAME_AMOUNT = "amount";
    private static final String PARAM_NAME_ENGINE = "engine";
    private static final String PARAM_NAME_STATUS = "newstatus";
    private static final String PARAM_NAME_COMMENTS = "comments";

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("Ќачало работы " + request.getParameter("command"));

        LOG.info(request.getParameter("id") + "- id " + request.getParameter("namber") + "- namber "
                + request.getParameter("newtype") + "- newtype " + request.getParameter("carrying") + "- carrying "
                + request.getParameter("amount") + "-  amount " + request.getParameter("engine") + "- engine "
                + request.getParameter("newstatus") + "- newstatus " + request.getParameter("comments")
                + "- comments ");

        // извлечение из запроса логина и парол€
        Integer id = Integer.parseInt(request.getParameter("id"));
        String namber = request.getParameter(PARAM_NAME_NAMBER);
        Integer type = Integer.parseInt(request.getParameter(PARAM_NAME_TYPE));
        Double carrying = Double.parseDouble(request.getParameter(PARAM_NAME_CARRYING));
        Double amount = Double.parseDouble(request.getParameter(PARAM_NAME_AMOUNT));
        Double engine = Double.parseDouble(request.getParameter(PARAM_NAME_ENGINE));
        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        Integer status = Integer.parseInt(request.getParameter(PARAM_NAME_STATUS));

        CarDAO dao = new CarDAO();
        Car car = dao.findEntityByNamber(namber);
        HttpSession session = request.getSession();

        if (car != null&&car.getId()!=id) {
            LOG.info("“акой норем машины уже есть");
            return ConfigurationManager.getProperty("path.page.admin");
        } else {
            List<Car> list = (List<Car>) session.getAttribute("cars");
            for (Car cars : list) {
                if (cars.getId() == id) {

                    cars.setNamber(namber);
                    cars.setType(TYPE.fromValue(type));
                    cars.setCarryingCar(carrying);
                    cars.setAmountCar(amount);
                    cars.setEnginePower(engine);
                    cars.setComments(comments);
                    cars.setStatusCar(Status.fromValue(status));
                    car = cars;

                }
            }

            LOG.info(car);
            session.setAttribute("cars", list);

            car = dao.update(car);

            return ConfigurationManager.getProperty("path.page.admin");

        }
    }

}
